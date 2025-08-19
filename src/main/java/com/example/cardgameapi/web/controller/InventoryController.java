package com.example.cardgameapi.web.controller;

import com.example.cardgameapi.entity.inventory.Inventory;
import com.example.cardgameapi.service.InventoryService;
import com.example.cardgameapi.web.dto.EquipmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{id}")
    public Inventory findInventoryById(@PathVariable Long id) {
        return inventoryService.findInventoryById(id);
    }

    @PostMapping("/equip")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void equip(@RequestBody EquipmentDto equipmentDto) {
        inventoryService.equip(equipmentDto);
    }

    @PostMapping("/unequip")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unEquip(@RequestBody EquipmentDto equipmentDto) {
        inventoryService.unEquip(equipmentDto);
    }


}
