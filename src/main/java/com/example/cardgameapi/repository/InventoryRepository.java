package com.example.cardgameapi.repository;

public interface InventoryRepository {

    void addInventoryItemToUser(Long userId, Long itemId);

}
