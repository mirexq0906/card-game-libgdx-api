package com.example.cardgameapi.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InventoryItem {

    private Long id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
