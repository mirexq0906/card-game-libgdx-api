package com.example.cardgameapi.web.mapper;

import com.example.cardgameapi.entity.InventoryItem;
import com.example.cardgameapi.web.response.InventoryItemResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class InventoryMapper {

    public List<InventoryItemResponse> toInventoryItemResponseList(List<InventoryItem> inventoryItems) {
        return inventoryItems.stream().map(this::toInventoryItemResponse).toList();
    }

    public InventoryItemResponse toInventoryItemResponse(InventoryItem inventoryItem) {
        InventoryItemResponse inventoryItemResponse = new InventoryItemResponse();
        inventoryItemResponse.setId(inventoryItem.getId());
        inventoryItemResponse.setName(inventoryItem.getName());
        inventoryItemResponse.setCreatedAt(inventoryItem.getCreatedAt().toString());
        inventoryItemResponse.setUpdatedAt(inventoryItem.getUpdatedAt().toString());
        return inventoryItemResponse;
    }

}
