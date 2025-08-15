package com.example.cardgameapi.repository;

import com.example.cardgameapi.entity.CollectionItem;
import com.example.cardgameapi.entity.InventoryItem;
import com.example.cardgameapi.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    List<User> findAll();

    Optional<User> findByUsername(String username);

    User create(User user);

    void updateResource(User user);

    void addInventoryItemToUser(long userId, long itemId);

    List<InventoryItem> getInventoryItemsByUserId(Long userId);

    void addCollectionItemToUser(long userId, long itemId);

    List<CollectionItem> getCollectionItemsByUserId(Long userId);

}
