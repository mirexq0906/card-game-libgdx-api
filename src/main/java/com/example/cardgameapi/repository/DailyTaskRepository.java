package com.example.cardgameapi.repository;

import com.example.cardgameapi.entity.daily_task.DailyTask;
import com.example.cardgameapi.entity.daily_task.UserTaskDaily;

import java.util.List;
import java.util.Optional;

public interface DailyTaskRepository {

    List<DailyTask> findAll();

    Optional<UserTaskDaily> findUserDailyTaskByIdAndUserId(Long userTaskDailyId, Long userId);

    List<UserTaskDaily> findUserDailyTasks(Long userId);

    void updateUserDailyTask(UserTaskDaily userTaskDaily);

    void deleteUserDailyTasks();

    void createPlayerDailyTaskList(List<UserTaskDaily> userTaskDailyList);

}
