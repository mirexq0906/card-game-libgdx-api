package com.example.cardgameapi.service;

import com.example.cardgameapi.entity.daily_task.UserTaskDaily;
import com.example.cardgameapi.entity.daily_task.TypeTask;

import java.util.List;

public interface DailyTaskService {

    List<UserTaskDaily> findUserDailyTasks();

    void takeReward(Long userTaskDailyId);

    void updateProgress(TypeTask typeTask);

    void refreshDailyTaskList();

}
