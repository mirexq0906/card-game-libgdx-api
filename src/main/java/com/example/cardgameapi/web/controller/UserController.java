package com.example.cardgameapi.web.controller;

import com.example.cardgameapi.entity.character.CharacterInstance;
import com.example.cardgameapi.entity.inventory.Inventory;
import com.example.cardgameapi.entity.inventory.InventoryType;
import com.example.cardgameapi.entity.user.User;
import com.example.cardgameapi.service.UserService;
import com.example.cardgameapi.web.mapper.UserMapper;
import com.example.cardgameapi.web.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/info")
    public UserResponse getUserInfo() {
        return userMapper.userToResponse((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    // TODO n + 1
    @GetMapping("/inventory-items")
    public Set<Inventory> getInventoryItems(@RequestParam(required = false) InventoryType inventoryType) {
        return userService.getInventoryItems(inventoryType);
    }

    // TODO n + 1
    @GetMapping("/collection-items")
    public List<CharacterInstance> getCollectionItems() {
        return userService.getCharacterInstances();
    }

}
