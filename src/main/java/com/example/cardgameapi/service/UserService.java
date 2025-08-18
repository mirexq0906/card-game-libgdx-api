package com.example.cardgameapi.service;

import com.example.cardgameapi.entity.character.CharacterInstance;
import com.example.cardgameapi.entity.inventory.Inventory;
import com.example.cardgameapi.entity.inventory.InventoryType;
import com.example.cardgameapi.entity.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface UserService extends UserDetailsService {

    User findById(Long id);

    Set<Inventory> getInventoryItems(InventoryType inventoryType);

    Set<CharacterInstance> getCharacterInstances();

}
