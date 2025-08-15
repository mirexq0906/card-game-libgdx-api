package com.example.cardgameapi.repository.mapper;

import com.example.cardgameapi.entity.CollectionItem;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectionItemRowMapper {

    public static CollectionItem mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        CollectionItem collectionItem = new CollectionItem();
        collectionItem.setId(resultSet.getLong("id"));
        collectionItem.setName(resultSet.getString("name"));
        collectionItem.setImage(resultSet.getString("image"));
        collectionItem.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        collectionItem.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
        return collectionItem;
    }

}
