package com.example.cardgameapi.service.ability;

import com.example.cardgameapi.entity.ability.Ability;
import com.example.cardgameapi.entity.battle.battle_logic.BattleCharacter;
import com.example.cardgameapi.service.ability.executor.AbilityExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AbilityService {

    private final List<AbilityExecutor> executors;

    public void executeAbility(BattleCharacter attacker, BattleCharacter defender, Long abilityId) {
        Ability ability = getAbilityFromTarget(attacker, abilityId);

        AbilityExecutor executor = executors.stream()
            .filter(e -> e.supports(ability))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("No executor found for ability: " + ability.getName()));

        executor.execute(ability, attacker, defender);
    }

    private Ability getAbilityFromTarget(BattleCharacter target, Long abilityId) {
        return target.getAbilities().stream().filter(ability -> ability.getId().equals(abilityId)).findFirst()
            .orElseThrow(() -> new RuntimeException("Ability not found"));
    }

}
