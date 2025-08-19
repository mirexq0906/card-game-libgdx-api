package com.example.cardgameapi.service;

import com.example.cardgameapi.entity.inventory.Inventory;
import com.example.cardgameapi.web.dto.EquipmentDto;

public interface InventoryService {

    Inventory findInventoryById(Long id);

    void equip(EquipmentDto equipmentDto);

    void unEquip(EquipmentDto equipmentDto);

}
