package com.example.cardgameapi.service;

import com.example.cardgameapi.web.dto.EquipmentDto;

public interface EquipmentService {

    void equip(EquipmentDto equipmentDto);

    void unEquip(EquipmentDto equipmentDto);

}
