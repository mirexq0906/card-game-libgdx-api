package com.example.cardgameapi.web.controller;

import com.example.cardgameapi.service.DailyTaskService;
import com.example.cardgameapi.web.mapper.DailyTaskMapper;
import com.example.cardgameapi.web.response.UserDailyTaskResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/daily-task")
@RequiredArgsConstructor
public class DailyTaskController {

    private final DailyTaskService dailyTaskService;
    private final DailyTaskMapper dailyTaskMapper;

    @GetMapping("/user")
    public List<UserDailyTaskResponse> getUserDailyTask() {
        return dailyTaskMapper.toUserDailyTaskResponseList(dailyTaskService.findUserDailyTasks());
    }

    @PostMapping("/take-reward/{userTaskDailyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void takeReward(@PathVariable Long userTaskDailyId) {
        dailyTaskService.takeReward(userTaskDailyId);
    }

}
