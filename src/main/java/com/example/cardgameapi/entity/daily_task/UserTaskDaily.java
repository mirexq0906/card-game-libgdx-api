package com.example.cardgameapi.entity.daily_task;

import com.example.cardgameapi.entity.user.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserTaskDaily {

    private Long id;
    private User user;
    private DailyTask dailyTask;
    private Integer progress;
    private Boolean completed;
    private Boolean rewardTaken;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
