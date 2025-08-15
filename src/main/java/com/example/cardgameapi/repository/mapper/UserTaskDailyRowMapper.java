package com.example.cardgameapi.repository.mapper;

import com.example.cardgameapi.entity.InventoryItem;
import com.example.cardgameapi.entity.daily_task.DailyTask;
import com.example.cardgameapi.entity.daily_task.UserTaskDaily;
import com.example.cardgameapi.entity.daily_task.Reward;
import com.example.cardgameapi.entity.daily_task.TypeTask;
import com.example.cardgameapi.entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserTaskDailyRowMapper {

    public static UserTaskDaily mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserTaskDaily playerTask = new UserTaskDaily();

        playerTask.setId(rs.getLong("user_task_daily_id"));
        playerTask.setProgress(rs.getInt("user_task_daily_progress"));
        playerTask.setCompleted(rs.getBoolean("user_task_daily_completed"));
        playerTask.setRewardTaken(rs.getBoolean("user_task_daily_reward_taken"));
        playerTask.setCreatedAt(rs.getTimestamp("user_task_daily_created_at").toLocalDateTime());
        playerTask.setUpdatedAt(rs.getTimestamp("user_task_daily_updated_at").toLocalDateTime());

        User user = new User();
        user.setId(rs.getLong("user_task_daily_user_id"));
        playerTask.setUser(user);

        DailyTask dailyTask = new DailyTask();
        dailyTask.setId(rs.getLong("daily_task_id"));
        dailyTask.setName(rs.getString("daily_task_name"));
        dailyTask.setDescription(rs.getString("daily_task_description"));
        dailyTask.setTarget(rs.getInt("daily_task_target"));
        dailyTask.setTypeTask(TypeTask.valueOf(rs.getString("daily_task_type_task")));
        dailyTask.setCreatedAt(rs.getTimestamp("daily_task_created_at").toLocalDateTime());
        dailyTask.setUpdatedAt(rs.getTimestamp("daily_task_updated_at").toLocalDateTime());

        Reward reward = new Reward();
        reward.setId(rs.getLong("reward_id"));
        reward.setGold(rs.getInt("reward_gold"));
        reward.setMana(rs.getInt("reward_mana"));
        reward.setAmountInventoryItems(rs.getInt("reward_amount_inventory_items"));
        reward.setCreatedAt(rs.getTimestamp("reward_created_at").toLocalDateTime());
        reward.setUpdatedAt(rs.getTimestamp("reward_updated_at").toLocalDateTime());

        InventoryItem item = new InventoryItem();
        item.setId(rs.getLong("inventory_id"));
        item.setName(rs.getString("inventory_name"));
        item.setImage(rs.getString("inventory_image"));
        item.setCreatedAt(rs.getTimestamp("inventory_created_at").toLocalDateTime());
        item.setUpdatedAt(rs.getTimestamp("inventory_updated_at").toLocalDateTime());

        reward.setInventoryItem(item);
        dailyTask.setReward(reward);
        playerTask.setDailyTask(dailyTask);

        return playerTask;
    }

}
