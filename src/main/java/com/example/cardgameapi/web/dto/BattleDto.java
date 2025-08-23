package com.example.cardgameapi.web.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class BattleDto {

    private List<Long> playerCardIds;
    private List<Long> enemyCardIds;
    private Long taskId;
    private UUID sessionId;
    private UUID defenderCharacterId;

}
