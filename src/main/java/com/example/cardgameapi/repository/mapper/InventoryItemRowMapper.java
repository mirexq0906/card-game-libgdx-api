package com.example.cardgameapi.repository.mapper;

import com.example.cardgameapi.entity.InventoryItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryItemRowMapper {

    public static InventoryItem mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        InventoryItem inventoryItem = new InventoryItem();
        inventoryItem.setId(resultSet.getLong("id"));
        inventoryItem.setName(resultSet.getString("name"));
        inventoryItem.setImage(resultSet.getString("image"));
        inventoryItem.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        inventoryItem.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
        return inventoryItem;
    }

}
