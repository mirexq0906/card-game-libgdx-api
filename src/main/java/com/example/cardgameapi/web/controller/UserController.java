package com.example.cardgameapi.web.controller;

import com.example.cardgameapi.service.UserService;
import com.example.cardgameapi.web.mapper.InventoryMapper;
import com.example.cardgameapi.web.response.InventoryItemResponse;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("inventory-items")
    public List<InventoryItemResponse> getInventoryItems() {
        return inventoryMapper.toInventoryItemResponseList(
                userService.getInventoryItems()
        );
    }

}
