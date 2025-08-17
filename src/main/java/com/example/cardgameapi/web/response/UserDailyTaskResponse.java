package com.example.cardgameapi.web.response;

import com.example.cardgameapi.entity.daily_task.TypeTask;
import lombok.Data;

@Data
public class UserDailyTaskResponse {

    private Long id;
    private DailyTaskResponse dailyTask;
    private Integer progress;
    private Boolean completed;
    private Boolean rewardTaken;
    private String createdAt;
    private String updatedAt;

    @Data
    public static class DailyTaskResponse {
        private Long id;
        private String name;
        private String description;
        private TypeTask typeTask;
        private RewardResponse reward;
        private Integer target;
        private String createdAt;
        private String updatedAt;
    }

    @Data
    public static class RewardResponse {
        private Long id;
        private Integer gold;
        private Integer mana;
        private InventoryItemResponse inventoryItem;
        private Integer amountInventoryItems;
        private String createdAt;
        private String updatedAt;
    }

}
