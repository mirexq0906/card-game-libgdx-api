package com.example.cardgameapi.service.impl;

import com.example.cardgameapi.entity.character.CharacterInstance;
import com.example.cardgameapi.entity.inventory.Equipment;
import com.example.cardgameapi.entity.inventory.Inventory;
import com.example.cardgameapi.entity.inventory.InventoryType;
import com.example.cardgameapi.entity.user.User;
import com.example.cardgameapi.exception.EntityNotFoundException;
import com.example.cardgameapi.repository.InventoryRepository;
import com.example.cardgameapi.service.InventoryService;
import com.example.cardgameapi.service.UserService;
import com.example.cardgameapi.web.dto.EquipmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final UserService userService;
    private final InventoryRepository inventoryRepository;

    @Override
    public Inventory findInventoryById(Long id) {
        return inventoryRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Inventory not found"));
    }

    @Override
    @Transactional
    public void equip(EquipmentDto equipmentDto) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findById(principal.getId());

        Inventory inventory = user.getInventories().stream()
            .filter(inv -> inv.getId().equals(equipmentDto.getEquipmentId()) && inv.getType() == InventoryType.EQUIPMENT)
            .findFirst()
            .orElseThrow(() -> new EntityNotFoundException("Equip not found"));

        if (!(inventory instanceof Equipment equipment)) {
            throw new IllegalStateException("Inventory item is not Equipment!");
        }

        CharacterInstance characterInstance = user.getCharacterInstances().stream()
            .filter(character -> character.getId().equals(equipmentDto.getCharacterId()))
            .findFirst().orElseThrow(() -> new EntityNotFoundException("Character not found"));
        Set<Equipment> characterInstanceEquipments = characterInstance.getEquipments();

        Equipment toSwap = characterInstanceEquipments.stream()
            .filter(e -> e.getSlotType() == (equipment).getSlotType())
            .findFirst()
            .orElse(null);

        if (toSwap != null) {
            characterInstanceEquipments.remove(toSwap);
            user.getInventories().add(toSwap);
        }

        characterInstanceEquipments.add(equipment);
        user.getInventories().remove(equipment);
    }

    @Override
    @Transactional
    public void unEquip(EquipmentDto equipmentDto) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findById(principal.getId());
        CharacterInstance characterInstance = user.getCharacterInstances().stream()
            .filter(character -> character.getId().equals(equipmentDto.getCharacterId()))
            .findFirst().orElseThrow(() -> new EntityNotFoundException("Character not found"));
        Equipment characterEquipment = characterInstance.getEquipments().stream()
            .filter(equipment -> equipment.getId().equals(equipmentDto.getEquipmentId()))
            .findFirst().orElseThrow(() -> new EntityNotFoundException("Equipment not found"));

        user.getInventories().add(characterEquipment);
        characterInstance.getEquipments().remove(characterEquipment);
    }

}
