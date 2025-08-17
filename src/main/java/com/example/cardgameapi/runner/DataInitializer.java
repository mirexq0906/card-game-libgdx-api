package com.example.cardgameapi.runner;

import com.example.cardgameapi.service.DailyTaskService;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements ApplicationRunner {

    private final DailyTaskService dailyTaskService;

    public DataInitializer(DailyTaskService dailyTaskService) {
        this.dailyTaskService = dailyTaskService;
    }

    @Override
    public void run(ApplicationArguments args) {
        dailyTaskService.refreshDailyTaskList();
    }

}
