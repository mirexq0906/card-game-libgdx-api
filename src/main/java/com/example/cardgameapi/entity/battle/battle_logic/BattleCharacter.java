package com.example.cardgameapi.entity.battle.battle_logic;

import lombok.Data;

import java.util.UUID;

@Data
public class BattleCharacter {

    private UUID id;
    private TurnType turnType;
    private Integer speed;
    private Integer hp;
    private Integer power;
    private String image;

}
