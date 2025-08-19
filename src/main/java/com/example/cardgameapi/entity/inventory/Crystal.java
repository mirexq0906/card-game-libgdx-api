package com.example.cardgameapi.entity.inventory;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Crystal extends Inventory {

    private String action;

}
