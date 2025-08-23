package com.example.cardgameapi.service.ability.executor;

import com.example.cardgameapi.entity.ability.Ability;
import com.example.cardgameapi.entity.ability.AbilityAttribute;
import com.example.cardgameapi.entity.ability.AbilityTarget;
import com.example.cardgameapi.entity.battle.battle_logic.BattleCharacter;
import org.springframework.stereotype.Component;

@Component
public class SpeedDebuffExecutor implements AbilityExecutor {

    @Override
    public boolean supports(Ability ability) {
        return ability.getAttribute() == AbilityAttribute.SPEED && ability.getTarget() == AbilityTarget.ENEMY;
    }

    @Override
    public void execute(Ability ability, BattleCharacter source, BattleCharacter target) {
        target.setSpeed(target.getSpeed() - ability.getValue());
    }

}
