package com.example.cardgameapi.entity.character;

import com.example.cardgameapi.entity.inventory.Equipment;
import com.example.cardgameapi.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class CharacterInstance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer level;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private CharacterTemplate template;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "character_instance_equipment",
        joinColumns = @JoinColumn(name = "character_instance_id"),
        inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private Set<Equipment> equipments = new HashSet<>();

    @Transient
    public int getEffectiveHp() {
        int bonusFromEquip = equipments.stream()
            .mapToInt(Equipment::getHp)
            .sum();
        int bonusFromLevel = level * 10;
        return template.getBaseHp() + bonusFromEquip + bonusFromLevel;
    }

    @Transient
    public int getEffectiveDamage() {
        int bonusFromEquip = equipments.stream()
            .mapToInt(Equipment::getPower)
            .sum();
        int bonusFromLevel = level * 2;
        return getTemplate().getBaseAutoAttack() + bonusFromEquip + bonusFromLevel;
    }

}
