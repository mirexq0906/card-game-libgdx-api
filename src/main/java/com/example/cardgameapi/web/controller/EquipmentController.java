package com.example.cardgameapi.web.controller;

import com.example.cardgameapi.service.EquipmentService;
import com.example.cardgameapi.web.dto.EquipmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/equipment")
@RequiredArgsConstructor
public class EquipmentController {

    private final EquipmentService equipmentService;

    @PostMapping("/equip")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void equip(@RequestBody EquipmentDto equipmentDto) {
        equipmentService.equip(equipmentDto);
    }

    @PostMapping("/unequip")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unEquip(@RequestBody EquipmentDto equipmentDto) {
        equipmentService.unEquip(equipmentDto);
    }

}
