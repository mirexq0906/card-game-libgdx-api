package com.example.cardgameapi.entity.set_bonus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class SetBonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String setName;
    private Integer piecesRequired;
    private String statType;
    private Integer bonusValue;

}
