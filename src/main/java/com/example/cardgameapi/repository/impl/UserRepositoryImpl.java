package com.example.cardgameapi.repository.impl;

import com.example.cardgameapi.entity.InventoryItem;
import com.example.cardgameapi.entity.user.User;
import com.example.cardgameapi.repository.UserRepository;
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
    public Optional<User> findById(Long id) {
        String query = """
                SELECT id, username, email, password, role, created_at, updated_at
                FROM users
                WHERE id = ?
                """;
        User user = jdbcTemplate.queryForObject(
                query,
                UserRowMapper::mapRow,
                id
        );

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String query = """
                SELECT id, username, email, password, role, created_at, updated_at
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
                RETURNING id, username, email, password, role, created_at, updated_at
                """;

        return jdbcTemplate.queryForObject(
                query,
                UserRowMapper::mapRow,
                user.getUsername(), user.getEmail(),
                user.getPassword(), user.getRole().toString()
        );
    }

    @Override
    public User update(User user) {
        String query = """
                UPDATE users
                SET username = ?, email = ?, password = ?, role = ?
                WHERE id = ?
                RETURNING id, username, email, password, role, created_at, updated_at
                """;

        return jdbcTemplate.queryForObject(
                query,
                UserRowMapper::mapRow,
                user.getUsername(), user.getEmail(),
                user.getPassword(), user.getRole().toString(),
                user.getId()
        );
    }

    @Override
    public Integer count() {
        String query = "SELECT COUNT(*) FROM users";

        return jdbcTemplate.queryForObject(
                query, Integer.class
        );
    }

    @Override
    public void addInventoryItemToUser(long userId, long itemId) {
        String query = "INSERT INTO user_inventory (user_id, inventory_id) VALUES (?, ?)";

        jdbcTemplate.update(query, userId, itemId);
    }

    @Override
    public List<InventoryItem> getInventoryItemsByUserId(Long userId) {
        String query = """
            SELECT inventories.id, inventories.name, inventories.created_at, inventories.updated_at
            FROM user_inventory
            JOIN inventories ON user_inventory.inventory_id = inventories.id
            WHERE user_inventory.user_id = ?
        """;

        return jdbcTemplate.query(query, InventoryItemRowMapper::mapRow, userId);
    }

}