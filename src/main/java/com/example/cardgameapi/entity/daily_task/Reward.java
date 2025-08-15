package com.example.cardgameapi.entity.daily_task;

import com.example.cardgameapi.entity.InventoryItem;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Reward {

    private Long id;
    private Integer gold;
    private Integer mana;
    private InventoryItem inventoryItem;
    private Integer amountInventoryItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
