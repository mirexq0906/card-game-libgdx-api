package com.example.cardgameapi.entity.battle;

import com.example.cardgameapi.entity.character.FractionType;
import com.example.cardgameapi.entity.character.RarityType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Enemy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private int hp;
    private int power;
    private int speed;

    @Enumerated(EnumType.STRING)
    private FractionType fractionType;

    @Enumerated(EnumType.STRING)
    private RarityType rarityType;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
