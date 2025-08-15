package com.example.cardgameapi.repository.mapper;

import com.example.cardgameapi.entity.user.Role;
import com.example.cardgameapi.entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class UserRowMapper {

    public static User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
        LocalDateTime updatedAt = resultSet.getTimestamp("updated_at").toLocalDateTime();

        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setRole(Role.valueOf(resultSet.getString("role")));
        user.setGold(resultSet.getInt("gold"));
        user.setMana(resultSet.getInt("mana"));
        user.setCreateTime(createdAt);
        user.setUpdateTime(updatedAt);
        return user;
    }
}
