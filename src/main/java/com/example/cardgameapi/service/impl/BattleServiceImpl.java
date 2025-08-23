package com.example.cardgameapi.service.impl;

import com.example.cardgameapi.entity.battle.BattleMode;
import com.example.cardgameapi.entity.battle.BattleTask;
import com.example.cardgameapi.entity.battle.UserBattleMode;
import com.example.cardgameapi.entity.battle.UserTaskProgress;
import com.example.cardgameapi.entity.character.CharacterInstance;
import com.example.cardgameapi.entity.user.User;
import com.example.cardgameapi.exception.EntityNotFoundException;
import com.example.cardgameapi.repository.*;
import com.example.cardgameapi.web.dto.CharacterToUserBattleModeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BattleServiceImpl {

    private final UserTaskProgressRepository userTaskProgressRepository;
    private final UserBattleModeRepository userBattleModeRepository;
    private final CharacterInstanceRepository characterInstanceRepository;
    private final BattleModeRepository battleModeRepository;
    private final UserRepository userRepository;
    private final BattleTaskRepository battleTaskRepository;

    public List<BattleMode> getBattleModes() {
        return battleModeRepository.findAll();
    }

    public UserBattleMode getOrCreateUserBattleBode(Long battleModeId) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userBattleModeRepository.findByUserIdAndBattleModeId(principal.getId(), battleModeId)
            .orElseGet(() -> {
                User user = userRepository.findById(principal.getId())
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
                BattleMode battleMode = battleModeRepository.findById(battleModeId)
                    .orElseThrow(() -> new EntityNotFoundException("BattleMode not found"));

                UserBattleMode userBattleMode = new UserBattleMode();
                userBattleMode.setBattleMode(battleMode);
                userBattleMode.setUser(user);
                return userBattleModeRepository.save(userBattleMode);
            });
    }

    @Transactional
    public void addCharacterToUserBattleMode(Long characterId, Long userBattleModeId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CharacterInstance characterInstance = characterInstanceRepository.findCharacterForUser(user.getId(), characterId)
            .orElseThrow(() -> new EntityNotFoundException("Character instance not found"));
        UserBattleMode userBattleMode = userBattleModeRepository.findByUserIdAndBattleModeId(user.getId(), userBattleModeId
            )
            .orElseThrow(() -> new EntityNotFoundException("User battle mode not found"));

        boolean alreadyAdded = userBattleMode.getCharacters().stream()
            .anyMatch(character -> character.getId().equals(characterId));

        if (alreadyAdded) {
            throw new IllegalArgumentException("Character already added to this battle mode");
        }

        userBattleMode.addCharacter(characterInstance);
    }

    @Transactional
    public void removeCharacterFromUserBattleMode(Long characterId, Long userBattleModeId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserBattleMode userBattleMode = userBattleModeRepository.findByUserIdAndBattleModeId(user.getId(), userBattleModeId)
            .orElseThrow(() -> new EntityNotFoundException("User battle mode not found"));

        userBattleMode.removeCharacterById(characterId);
    }

    public List<BattleTask> getBattleTasksByBattleModeId(Long battleModeId) {
        return battleTaskRepository.findAllByBattleModeId(battleModeId);
    }

    public List<UserTaskProgress> getUserTaskProgressList() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userTaskProgressRepository.findAllByUserId(principal.getId());
    }

}
