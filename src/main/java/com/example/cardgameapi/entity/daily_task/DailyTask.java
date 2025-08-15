package com.example.cardgameapi.entity.daily_task;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DailyTask {

    private Long id;
    private String name;
    private String description;
    private TypeTask typeTask;
    private Reward reward;
    private Integer target;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
