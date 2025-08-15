package com.example.cardgameapi.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CollectionItem {

    private Long id;
    private String name;
    private String image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
