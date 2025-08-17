package com.example.cardgameapi.service.impl;

import com.example.cardgameapi.entity.daily_task.DailyTask;
import com.example.cardgameapi.entity.daily_task.Reward;
import com.example.cardgameapi.entity.daily_task.UserTaskDaily;
import com.example.cardgameapi.entity.daily_task.TypeTask;
import com.example.cardgameapi.entity.user.User;
import com.example.cardgameapi.exception.EntityNotFoundException;
import com.example.cardgameapi.repository.DailyTaskRepository;
import com.example.cardgameapi.repository.InventoryRepository;
import com.example.cardgameapi.repository.UserRepository;
import com.example.cardgameapi.repository.UserTaskDailyRepository;
import com.example.cardgameapi.service.DailyTaskService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyTaskServiceImpl implements DailyTaskService {

    private final DailyTaskRepository dailyTaskRepository;
    private final UserTaskDailyRepository userTaskDailyRepository;
    private final UserRepository userRepository;

    @Override
    public List<UserTaskDaily> findUserDailyTasks() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userTaskDailyRepository.findAllByUserId(user.getId());
    }

    @Override
    @Transactional
    public void takeReward(Long userTaskDailyId) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(principal.getId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        UserTaskDaily userTaskDaily = userTaskDailyRepository.findByIdAndUserId(userTaskDailyId, user.getId())
            .orElseThrow(() -> new EntityNotFoundException("User Task Daily Not Found"));

        if (!userTaskDaily.getCompleted()) {
            throw new IllegalStateException("User Task Daily Not Completed");
        }

        Reward reward = userTaskDaily.getDailyTask().getReward();

        if (reward.getInventory() != null) {
            user.getInventories().add(reward.getInventory());
        }

        user.setMana(user.getMana() + reward.getMana());
        user.setGold(user.getGold() + reward.getGold());
        userTaskDaily.setRewardTaken(true);
    }

    @Override
    @Transactional
    public void updateProgress(TypeTask typeTask) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UserTaskDaily> userDailyTasks = userTaskDailyRepository.findAllByUserId(user.getId());

        for (UserTaskDaily userTaskDaily : userDailyTasks) {
            if (userTaskDaily.getCompleted()) {
                continue;
            }

            if (userTaskDaily.getDailyTask().getTypeTask() == typeTask) {
                userTaskDaily.setProgress(userTaskDaily.getProgress() + 1);

                if (userTaskDaily.getProgress().equals(userTaskDaily.getDailyTask().getTarget())) {
                    userTaskDaily.setCompleted(true);
                }

                break;
            }
        }
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void refreshDailyTaskList() {
        List<User> users = userRepository.findAll();
        List<DailyTask> dailyTasks = dailyTaskRepository.findAll();
        List<UserTaskDaily> userTaskDailyList = new ArrayList<>();

        users.forEach(user -> {
            Collections.shuffle(dailyTasks);
            dailyTasks.stream().limit(5).forEach(dailyTask -> {
                UserTaskDaily userTaskDaily = new UserTaskDaily();
                userTaskDaily.setCompleted(false);
                userTaskDaily.setProgress(0);
                userTaskDaily.setDailyTask(dailyTask);
                userTaskDaily.setUser(user);
                userTaskDailyList.add(userTaskDaily);
            });
        });

        userTaskDailyRepository.deleteAll();
        userTaskDailyRepository.saveAll(userTaskDailyList);
    }

}
