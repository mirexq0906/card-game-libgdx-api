package com.example.cardgameapi.entity.inventory;

import com.example.cardgameapi.entity.set_bonus.SetBonus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Equipment extends Inventory {

    @Enumerated(EnumType.STRING)
    private SlotType slotType;
    private Integer power;
    private Integer hp;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "equipment_set_bonus",
        joinColumns = @JoinColumn(name = "equipment_id"),
        inverseJoinColumns = @JoinColumn(name = "set_bonus_id")
    )
    private Set<SetBonus> setBonuses;

}
