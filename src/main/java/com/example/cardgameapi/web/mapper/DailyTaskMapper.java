package com.example.cardgameapi.web.mapper;

import com.example.cardgameapi.entity.daily_task.UserTaskDaily;
import com.example.cardgameapi.web.response.InventoryItemResponse;
import com.example.cardgameapi.web.response.UserDailyTaskResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DailyTaskMapper {

    public List<UserDailyTaskResponse> toUserDailyTaskResponseList(List<UserTaskDaily> userTaskDailyList) {
        return userTaskDailyList.stream().map(this::toUserDailyTaskResponse).collect(Collectors.toList());
    }

    public UserDailyTaskResponse toUserDailyTaskResponse(UserTaskDaily userTaskDaily) {
        UserDailyTaskResponse userDailyTaskResponse = new UserDailyTaskResponse();
        UserDailyTaskResponse.DailyTaskResponse dailyTaskResponse = new UserDailyTaskResponse.DailyTaskResponse();
        UserDailyTaskResponse.RewardResponse rewardResponse = new UserDailyTaskResponse.RewardResponse();
        InventoryItemResponse inventoryItemResponse = new InventoryItemResponse();

        userDailyTaskResponse.setId(userTaskDaily.getId());
        userDailyTaskResponse.setProgress(userTaskDaily.getProgress());
        userDailyTaskResponse.setDailyTask(dailyTaskResponse);
        userDailyTaskResponse.setCompleted(userTaskDaily.getCompleted());
        userDailyTaskResponse.setRewardTaken(userTaskDaily.getRewardTaken());
        userDailyTaskResponse.setCreatedAt(userTaskDaily.getCreatedAt().toString());
        userDailyTaskResponse.setUpdatedAt(userTaskDaily.getUpdatedAt().toString());

        dailyTaskResponse.setId(userTaskDaily.getDailyTask().getId());
        dailyTaskResponse.setName(userTaskDaily.getDailyTask().getName());
        dailyTaskResponse.setDescription(userTaskDaily.getDailyTask().getDescription());
        dailyTaskResponse.setTypeTask(userTaskDaily.getDailyTask().getTypeTask());
        dailyTaskResponse.setReward(rewardResponse);
        dailyTaskResponse.setTarget(userTaskDaily.getDailyTask().getTarget());
        dailyTaskResponse.setCreatedAt(userTaskDaily.getDailyTask().getCreatedAt().toString());
        dailyTaskResponse.setUpdatedAt(userTaskDaily.getDailyTask().getUpdatedAt().toString());

        rewardResponse.setId(userTaskDaily.getDailyTask().getReward().getId());
        rewardResponse.setGold(userTaskDaily.getDailyTask().getReward().getGold());
        rewardResponse.setMana(userTaskDaily.getDailyTask().getReward().getMana());
        rewardResponse.setInventoryItem(inventoryItemResponse);
        rewardResponse.setAmountInventoryItems(userTaskDaily.getDailyTask().getReward().getAmountInventoryItems());
        rewardResponse.setCreatedAt(userTaskDaily.getDailyTask().getReward().getCreatedAt().toString());
        rewardResponse.setUpdatedAt(userTaskDaily.getDailyTask().getReward().getUpdatedAt().toString());

        inventoryItemResponse.setId(userTaskDaily.getDailyTask().getReward().getInventoryItem().getId());
        inventoryItemResponse.setName(userTaskDaily.getDailyTask().getReward().getInventoryItem().getName());
        inventoryItemResponse.setImage(userTaskDaily.getDailyTask().getReward().getInventoryItem().getImage());
        inventoryItemResponse.setCreatedAt(userTaskDaily.getDailyTask().getReward().getInventoryItem().getCreatedAt().toString());
        inventoryItemResponse.setUpdatedAt(userTaskDaily.getDailyTask().getReward().getInventoryItem().getUpdatedAt().toString());

        return userDailyTaskResponse;
    }

}
