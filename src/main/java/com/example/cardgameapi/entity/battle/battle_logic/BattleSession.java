package com.example.cardgameapi.entity.battle.battle_logic;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class BattleSession {

    private UUID sessionId;
    private Long userId;
    private Long taskId;
    private List<BattleCharacter> queue = new ArrayList<>();
    private List<BattleCharacter> deathCharacters = new ArrayList<>();
    private List<BattleCharacter> battleCharacters = new ArrayList<>();
    private UUID turnCharacterId;
    private TurnType turnType;
    private TurnType winningTurnType;
    private boolean gameOver;

}
