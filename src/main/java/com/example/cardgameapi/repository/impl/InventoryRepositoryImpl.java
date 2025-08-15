package com.example.cardgameapi.repository.impl;

import com.example.cardgameapi.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InventoryRepositoryImpl implements InventoryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void addInventoryItemToUser(Long userId, Long itemId) {
        String query = """
            INSERT INTO user_inventory (user_id, inventory_id)
            VALUES (?, ?)
            """;

        jdbcTemplate.update(query, userId, itemId);
    }

}
