package com.example.cardgameapi.repository;

import com.example.cardgameapi.entity.InventoryItem;
import com.example.cardgameapi.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    User create(User user);

    User update(User user);

    Integer count();

    void addInventoryItemToUser(long userId, long itemId);

    List<InventoryItem> getInventoryItemsByUserId(Long userId);

}