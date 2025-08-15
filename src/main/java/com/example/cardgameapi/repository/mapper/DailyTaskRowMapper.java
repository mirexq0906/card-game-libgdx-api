package com.example.cardgameapi.repository.mapper;

import com.example.cardgameapi.entity.InventoryItem;
import com.example.cardgameapi.entity.daily_task.DailyTask;
import com.example.cardgameapi.entity.daily_task.TypeTask;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DailyTaskRowMapper {

    public static DailyTask mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        DailyTask dailyTask = new DailyTask();
        dailyTask.setId(resultSet.getLong("id"));
        dailyTask.setName(resultSet.getString("name"));
        dailyTask.setDescription(resultSet.getString("description"));
        dailyTask.setTypeTask(TypeTask.valueOf(resultSet.getString("type_task")));
        dailyTask.setCreatedAt(resultSet.getTimestamp("created_at").toLocalDateTime());
        dailyTask.setUpdatedAt(resultSet.getTimestamp("updated_at").toLocalDateTime());
        return dailyTask;
    }

}
