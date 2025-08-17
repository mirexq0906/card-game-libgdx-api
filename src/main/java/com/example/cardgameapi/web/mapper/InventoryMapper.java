package com.example.cardgameapi.web.mapper;

import com.example.cardgameapi.entity.inventory.Inventory;
import com.example.cardgameapi.web.response.InventoryItemResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class InventoryMapper {

    public List<InventoryItemResponse> toInventoryItemResponseList(Set<Inventory> inventoryItems) {
        return inventoryItems.stream().map(this::toInventoryItemResponse).toList();
    }

    public InventoryItemResponse toInventoryItemResponse(Inventory inventoryItem) {
        InventoryItemResponse inventoryItemResponse = new InventoryItemResponse();
        inventoryItemResponse.setId(inventoryItem.getId());
        inventoryItemResponse.setName(inventoryItem.getName());
        inventoryItemResponse.setImage(inventoryItem.getImage());
        inventoryItemResponse.setCreatedAt(inventoryItem.getCreatedAt() != null ? inventoryItem.getCreatedAt().toString() : null);
        inventoryItemResponse.setUpdatedAt(inventoryItem.getUpdatedAt() != null ? inventoryItem.getUpdatedAt().toString() : null);
        return inventoryItemResponse;
    }

}
