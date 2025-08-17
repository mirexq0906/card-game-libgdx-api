package com.example.cardgameapi.service;

import com.example.cardgameapi.entity.collection.Character;
import com.example.cardgameapi.entity.inventory.Inventory;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface UserService extends UserDetailsService {

    Set<Inventory> getInventoryItems();

    Set<Character> getCollectionItems();

}
