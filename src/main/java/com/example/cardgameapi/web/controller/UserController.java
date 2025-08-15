package com.example.cardgameapi.web.controller;

import com.example.cardgameapi.entity.user.User;
import com.example.cardgameapi.service.UserService;
import com.example.cardgameapi.web.mapper.CollectionMapper;
import com.example.cardgameapi.web.mapper.InventoryMapper;
import com.example.cardgameapi.web.mapper.UserMapper;
import com.example.cardgameapi.web.response.CollectionItemResponse;
import com.example.cardgameapi.web.response.InventoryItemResponse;
import com.example.cardgameapi.web.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final InventoryMapper inventoryMapper;
    private final UserMapper userMapper;
    private final CollectionMapper collectionMapper;

    @GetMapping("/info")
    public UserResponse getUserInfo() {
        return userMapper.userToResponse((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    @GetMapping("/inventory-items")
    public List<InventoryItemResponse> getInventoryItems() {
        return inventoryMapper.toInventoryItemResponseList(
            userService.getInventoryItems()
        );
    }

    @GetMapping("/collection-items")
    public List<CollectionItemResponse> getCollectionItems() {
        return collectionMapper.toCollectionItemResponseList(
            userService.getCollectionItems()
        );
    }

}
