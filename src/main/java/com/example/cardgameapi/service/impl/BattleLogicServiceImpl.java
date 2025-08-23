package com.example.cardgameapi.service.impl;

import com.example.cardgameapi.entity.battle.BattleTask;
import com.example.cardgameapi.entity.battle.ProgressType;
import com.example.cardgameapi.entity.battle.UserTaskProgress;
import com.example.cardgameapi.entity.battle.battle_logic.BattleCharacter;
import com.example.cardgameapi.entity.battle.battle_logic.BattleSession;
import com.example.cardgameapi.entity.battle.Enemy;
import com.example.cardgameapi.entity.battle.battle_logic.TurnType;
import com.example.cardgameapi.entity.character.CharacterInstance;
import com.example.cardgameapi.entity.user.User;
import com.example.cardgameapi.exception.EntityNotFoundException;
import com.example.cardgameapi.repository.*;
import com.example.cardgameapi.service.ability.AbilityService;
import com.example.cardgameapi.web.dto.BattleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BattleLogicServiceImpl {

    private final CharacterInstanceRepository characterInstanceRepository;
    private final UserTaskProgressRepository userTaskProgressRepository;
    private final BattleTaskRepository battleTaskRepository;
    private final EnemyRepository enemyRepository;
    private final UserRepository userRepository;
    private final AbilityService abilityService;

    private final Map<UUID, BattleSession> battleSessions = new ConcurrentHashMap<>();

    public UUID createBattleSession(BattleDto battleDto) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BattleSession battleSession = new BattleSession();
        List<CharacterInstance> characterInstances = characterInstanceRepository.findAllById(battleDto.getPlayerCardIds());
        List<Enemy> enemies = enemyRepository.findAllById(battleDto.getEnemyCardIds());
        List<BattleCharacter> queue = new ArrayList<>();
        List<BattleCharacter> sordetCharacterList = getSortedBattleCharacter(characterInstances, enemies);

        battleSession.setSessionId(UUID.randomUUID());
        battleSession.setBattleCharacters(sordetCharacterList);
        battleSession.setTurnCharacterId(sordetCharacterList.getLast().getId());
        battleSession.setTurnType(sordetCharacterList.getLast().getTurnType());
        battleSession.setQueue(queue);
        battleSession.setUserId(principal.getId());
        battleSession.setTaskId(battleDto.getTaskId());
        System.out.println(battleSession);
        battleSessions.put(battleSession.getSessionId(), battleSession);

        return battleSession.getSessionId();
    }

    public BattleSession getBattleSession(UUID sessionId) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        BattleSession battleSession = battleSessions.get(sessionId);


        if (!battleSession.getUserId().equals(principal.getId())) {
            throw new RuntimeException("User does not belong to this session");
        }

        return battleSession;
    }

    public BattleSession makeMove(BattleDto battleDto) {
        BattleSession battleSession = battleSessions.get(battleDto.getSessionId());

        if (battleSession == null) {
            throw new RuntimeException("Battle session not found");
        }

        UUID turnCharacterId = battleSession.getTurnCharacterId();
        BattleCharacter attacker = battleSession.getBattleCharacters().stream()
            .filter(battleCharacter -> battleCharacter.getId().equals(turnCharacterId))
            .findFirst()
            .orElseThrow(() -> new EntityNotFoundException("attacker not found"));
        BattleCharacter defender = battleSession.getBattleCharacters().stream()
            .filter(battleCharacter -> battleCharacter.getId().equals(battleDto.getDefenderCharacterId()))
            .findFirst()
            .orElseThrow(() -> new EntityNotFoundException("defender not found"));

        if (battleDto.getAbilityId() != null) {
            abilityService.executeAbility(attacker, defender, battleDto.getAbilityId());
        } else {
            defender.setHp(defender.getHp() - attacker.getPower());
        }
        
        battleSession.getQueue().add(attacker);

        if (defender.getHp() <= 0) {
            battleSession.getDeathCharacters().add(defender);

            List<BattleCharacter> modifiable = new ArrayList<>(battleSession.getBattleCharacters());
            modifiable.remove(defender);
            battleSession.setBattleCharacters(modifiable);
        }

        if (checkWinner(battleSession)) {
            return battleSession;
        }

        BattleCharacter fasterCharacter = getFasterBattleCharacter(battleSession);

        if (fasterCharacter == null) {
            battleSession.getQueue().clear();
            fasterCharacter = getFasterBattleCharacter(battleSession);
        }

        battleSession.setTurnType(fasterCharacter.getTurnType());
        battleSession.setTurnCharacterId(fasterCharacter.getId());

        return battleSession;
    }

    @Transactional
    public void finishBattleSession(BattleDto battleDto) {
        BattleSession battleSession = battleSessions.get(battleDto.getSessionId());

        if (!battleSession.isGameOver()) {
            throw new RuntimeException("Battle session is over");
        }

        BattleTask battleTask = battleTaskRepository.findById(battleSession.getTaskId()).orElse(null);
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(principal.getId()).orElseThrow(() -> new EntityNotFoundException("user not found"));

        if (battleSession.getWinningTurnType() == TurnType.PLAYER) {
            if (battleTask.getReward().getInventory() != null) {
                user.getInventories().add(battleTask.getReward().getInventory());
            }

            user.setGold(user.getGold() + battleTask.getReward().getGold());
            user.setMana(user.getMana() + battleTask.getReward().getMana());
        }

        UserTaskProgress userTaskProgress = new UserTaskProgress();
        userTaskProgress.setUser(principal);
        userTaskProgress.setProgressType(ProgressType.FINISHED);
        userTaskProgress.setBattleTask(battleTask);
        userTaskProgressRepository.save(userTaskProgress);
    }

    private boolean checkWinner(BattleSession battleSession) {
        Map<TurnType, List<BattleCharacter>> grouping = battleSession.getBattleCharacters().stream()
            .collect(Collectors.groupingBy(BattleCharacter::getTurnType));

        if (grouping.size() == 1) {
            TurnType winner = grouping.keySet().iterator().next();
            battleSession.setGameOver(true);
            battleSession.setWinningTurnType(winner);
            return true;
        }

        return false;
    }

    private List<BattleCharacter> getSortedBattleCharacter(
        List<CharacterInstance> characterInstances,
        List<Enemy> enemies
    ) {
        return Stream.concat(
                characterInstances.stream()
                    .map(characterInstance -> {
                        BattleCharacter battleCharacter = new BattleCharacter();
                        battleCharacter.setId(UUID.randomUUID());
                        battleCharacter.setTurnType(TurnType.PLAYER);
                        battleCharacter.setSpeed(characterInstance.getEffectiveSpeed());
                        battleCharacter.setHp(characterInstance.getEffectiveHp());
                        battleCharacter.setAbilities(characterInstance.getAbilities());
                        battleCharacter.setPower(characterInstance.getEffectiveHp());
                        battleCharacter.setImage(characterInstance.getTemplate().getImage());
                        return battleCharacter;
                    }),
                enemies.stream()
                    .map(enemy -> {
                        BattleCharacter battleCharacter = new BattleCharacter();
                        battleCharacter.setId(UUID.randomUUID());
                        battleCharacter.setTurnType(TurnType.ENEMY);
                        battleCharacter.setSpeed(enemy.getSpeed());
                        battleCharacter.setHp(enemy.getHp());
                        battleCharacter.setAbilities(new ArrayList<>());
                        battleCharacter.setPower(enemy.getPower());
                        battleCharacter.setImage(enemy.getImage());
                        return battleCharacter;
                    })
            )
            .sorted(Comparator.comparingInt(BattleCharacter::getSpeed))
            .toList();
    }

    private BattleCharacter getFasterBattleCharacter(BattleSession battleSession) {
        return battleSession.getBattleCharacters().stream()
            .filter(battleCharacter -> battleSession.getDeathCharacters().stream()
                .noneMatch(q -> q.getId().equals(battleCharacter.getId())))
            .filter(battleCharacter -> battleSession.getQueue().stream()
                .noneMatch(q -> q.getId().equals(battleCharacter.getId())))
            .max(Comparator.comparingInt(BattleCharacter::getSpeed))
            .orElse(null);
    }

}
