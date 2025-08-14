package com.example.cardgameapi.web.response;

import lombok.Data;

@Data
public class InventoryItemResponse {

    private Long id;
    private String name;
    private String createdAt;
    private String updatedAt;

}
