package com.example.cardgameapi.service.ability.executor;

import com.example.cardgameapi.entity.ability.Ability;
import com.example.cardgameapi.entity.battle.battle_logic.BattleCharacter;

public interface AbilityExecutor {

    boolean supports(Ability ability);

    void execute(Ability ability, BattleCharacter source, BattleCharacter target);

}
