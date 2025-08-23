package com.example.cardgameapi.entity.ability;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Ability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String image;
    private String description;
    private Integer value;

    @Enumerated(EnumType.STRING)
    private AbilityType type;

    @Enumerated(EnumType.STRING)
    private AbilityTarget target;

    @Enumerated(EnumType.STRING)
    private AbilityAttribute attribute;

}
