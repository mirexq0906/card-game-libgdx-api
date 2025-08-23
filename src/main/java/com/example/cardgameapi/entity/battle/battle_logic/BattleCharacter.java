package com.example.cardgameapi.entity.battle.battle_logic;

import com.example.cardgameapi.entity.ability.Ability;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BattleCharacter {

    private UUID id;
    private TurnType turnType;
    private Integer speed;
    private Integer hp;
    private Integer power;
    private String image;
    private List<Ability> abilities;

}
