package com.example.cardgameapi.service;

import com.example.cardgameapi.entity.InventoryItem;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<InventoryItem> getInventoryItems();

}
