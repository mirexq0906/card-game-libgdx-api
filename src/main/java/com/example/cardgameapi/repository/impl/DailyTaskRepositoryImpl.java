package com.example.cardgameapi.repository.impl;

import com.example.cardgameapi.entity.daily_task.DailyTask;
import com.example.cardgameapi.entity.daily_task.UserTaskDaily;
import com.example.cardgameapi.repository.DailyTaskRepository;
import com.example.cardgameapi.repository.mapper.DailyTaskRowMapper;
import com.example.cardgameapi.repository.mapper.UserTaskDailyRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DailyTaskRepositoryImpl implements DailyTaskRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<DailyTask> findAll() {
        String query = """
            SELECT id, name, description, type_task, created_at, updated_at
            FROM daily_tasks
            """;

        return jdbcTemplate.query(query, DailyTaskRowMapper::mapRow);
    }

    @Override
    public Optional<UserTaskDaily> findUserDailyTaskByIdAndUserId(Long userTaskDailyId, Long userId) {
        String query = """
            SELECT
                user_task_daily.id AS user_task_daily_id,
                user_task_daily.user_id AS user_task_daily_user_id,
                user_task_daily.progress AS user_task_daily_progress,
                user_task_daily.completed AS user_task_daily_completed,
                user_task_daily.reward_taken AS user_task_daily_reward_taken,
                user_task_daily.created_at AS user_task_daily_created_at,
                user_task_daily.updated_at AS user_task_daily_updated_at,
                daily_tasks.id AS daily_task_id,
                daily_tasks.name AS daily_task_name,
                daily_tasks.description AS daily_task_description,
                daily_tasks.target AS daily_task_target,
                daily_tasks.type_task AS daily_task_type_task,
                daily_tasks.created_at AS daily_task_created_at,
                daily_tasks.updated_at AS daily_task_updated_at,
                rewards.id AS reward_id,
                rewards.gold AS reward_gold,
                rewards.mana AS reward_mana,
                rewards.amount_inventory_items AS reward_amount_inventory_items,
                rewards.created_at AS reward_created_at,
                rewards.updated_at AS reward_updated_at,
                inventories.id AS inventory_id,
                inventories.name AS inventory_name,
                inventories.image AS inventory_image,
                inventories.created_at AS inventory_created_at,
                inventories.updated_at AS inventory_updated_at
            FROM user_task_daily
            JOIN daily_tasks ON daily_tasks.id = user_task_daily.daily_task_id
            JOIN rewards ON rewards.id = daily_tasks.reward_id
            JOIN inventories ON inventories.id = rewards.inventory_id
            WHERE user_task_daily.id = ? AND user_task_daily.user_id = ?
            """;

        List<UserTaskDaily> userDailyTask = jdbcTemplate.query(query, UserTaskDailyRowMapper::mapRow, userTaskDailyId, userId);
        return userDailyTask.stream().findFirst();
    }

    @Override
    public List<UserTaskDaily> findUserDailyTasks(Long userId) {
        String query = """
            SELECT
                user_task_daily.id AS user_task_daily_id,
                user_task_daily.user_id AS user_task_daily_user_id,
                user_task_daily.progress AS user_task_daily_progress,
                user_task_daily.completed AS user_task_daily_completed,
                user_task_daily.reward_taken AS user_task_daily_reward_taken,
                user_task_daily.created_at AS user_task_daily_created_at,
                user_task_daily.updated_at AS user_task_daily_updated_at,
                daily_tasks.id AS daily_task_id,
                daily_tasks.name AS daily_task_name,
                daily_tasks.description AS daily_task_description,
                daily_tasks.target AS daily_task_target,
                daily_tasks.type_task AS daily_task_type_task,
                daily_tasks.created_at AS daily_task_created_at,
                daily_tasks.updated_at AS daily_task_updated_at,
                rewards.id AS reward_id,
                rewards.gold AS reward_gold,
                rewards.mana AS reward_mana,
                rewards.amount_inventory_items AS reward_amount_inventory_items,
                rewards.created_at AS reward_created_at,
                rewards.updated_at AS reward_updated_at,
                inventories.id AS inventory_id,
                inventories.name AS inventory_name,
                inventories.image AS inventory_image,
                inventories.created_at AS inventory_created_at,
                inventories.updated_at AS inventory_updated_at
            FROM user_task_daily
            JOIN daily_tasks ON daily_tasks.id = user_task_daily.daily_task_id
            JOIN rewards ON rewards.id = daily_tasks.reward_id
            JOIN inventories ON inventories.id = rewards.inventory_id
            WHERE user_task_daily.user_id = ?
            """;

        return jdbcTemplate.query(query, UserTaskDailyRowMapper::mapRow, userId);
    }

    @Override
    public void updateUserDailyTask(UserTaskDaily userTaskDaily) {
        String query = """
            UPDATE user_task_daily
            SET progress = ?, completed = ?, reward_taken = ?, updated_at = ?
            WHERE id = ?
            """;

        jdbcTemplate.update(
            query,
            userTaskDaily.getProgress(),
            userTaskDaily.getCompleted(),
            userTaskDaily.getRewardTaken(),
            LocalDateTime.now(),
            userTaskDaily.getId()
        );
    }

    @Override
    public void deleteUserDailyTasks() {
        String query = """
            DELETE FROM user_task_daily
            """;

        jdbcTemplate.update(query);
    }

    @Override
    public void createPlayerDailyTaskList(List<UserTaskDaily> userTaskDailyList) {
        String query = """
            INSERT INTO user_task_daily (progress, completed, user_id, daily_task_id)
            VALUES (?, ?, ?, ?)
            """;

        jdbcTemplate.batchUpdate(query, userTaskDailyList, userTaskDailyList.size(),
            (ps, playerTask) -> {
                ps.setInt(1, playerTask.getProgress());
                ps.setBoolean(2, playerTask.getCompleted());
                ps.setLong(3, playerTask.getUser().getId());
                ps.setLong(4, playerTask.getDailyTask().getId());
            }
        );
    }

}
