package com.example.cardgameapi.repository.impl;

import com.example.cardgameapi.entity.CollectionItem;
import com.example.cardgameapi.entity.InventoryItem;
import com.example.cardgameapi.entity.user.User;
import com.example.cardgameapi.repository.UserRepository;
import com.example.cardgameapi.repository.mapper.CollectionItemRowMapper;
import com.example.cardgameapi.repository.mapper.InventoryItemRowMapper;
import com.example.cardgameapi.repository.mapper.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        String query = """
            SELECT id, username, email, password, role, gold, mana, created_at, updated_at
            FROM users
            """;

        return jdbcTemplate.query(query, UserRowMapper::mapRow);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String query = """
            SELECT id, username, email, password, role, gold, mana, created_at, updated_at
            FROM users
            WHERE username = ?
            """;

        User user = jdbcTemplate.queryForObject(
            query,
            UserRowMapper::mapRow,
            username
        );

        return Optional.ofNullable(user);
    }

    @Override
    public User create(User user) {
        String query = """
            INSERT INTO users (username, email, password, role)
            VALUES (?, ?, ?, ?)
            RETURNING id, username, email, password, role, gold, mana, created_at, updated_at
            """;

        return jdbcTemplate.queryForObject(
            query,
            UserRowMapper::mapRow,
            user.getUsername(), user.getEmail(),
            user.getPassword(), user.getRole().toString()
        );
    }

    @Override
    public void updateResource(User user) {
        String query = """
            UPDATE users
            SET gold = ?, mana = ?
            WHERE id = ?
            """;

        jdbcTemplate.update(query, user.getGold(), user.getMana(), user.getId());
    }

    @Override
    public void addInventoryItemToUser(long userId, long itemId) {
        String query = "INSERT INTO user_inventory (user_id, inventory_id) VALUES (?, ?)";

        jdbcTemplate.update(query, userId, itemId);
    }

    @Override
    public List<InventoryItem> getInventoryItemsByUserId(Long userId) {
        String query = """
                SELECT inventories.id, inventories.name, inventories.image, inventories.created_at, inventories.updated_at
                FROM user_inventory
                JOIN inventories ON user_inventory.inventory_id = inventories.id
                WHERE user_inventory.user_id = ?
            """;

        return jdbcTemplate.query(query, InventoryItemRowMapper::mapRow, userId);
    }

    @Override
    public void addCollectionItemToUser(long userId, long itemId) {
        String query = "INSERT INTO user_collection (user_id, collection_id) VALUES (?, ?)";

        jdbcTemplate.update(query, userId, itemId);
    }

    @Override
    public List<CollectionItem> getCollectionItemsByUserId(Long userId) {
        String query = """
                SELECT collections.id, collections.name, collections.image, collections.created_at, collections.updated_at
                FROM user_collection
                JOIN collections ON user_collection.collection_id = collections.id
                WHERE user_collection.user_id = ?
            """;

        return jdbcTemplate.query(query, CollectionItemRowMapper::mapRow, userId);
    }


}
