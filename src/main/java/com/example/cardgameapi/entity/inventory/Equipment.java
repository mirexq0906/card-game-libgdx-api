package com.example.cardgameapi.entity.inventory;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Equipment extends Inventory {

    @Enumerated(EnumType.STRING)
    private SlotType slotType;

    private Integer power;
    private Integer hp;

}
