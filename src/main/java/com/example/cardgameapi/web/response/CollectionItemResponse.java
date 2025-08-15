package com.example.cardgameapi.web.response;

import lombok.Data;

@Data
public class CollectionItemResponse {

    private Long id;
    private String name;
    private String image;
    private String createdAt;
    private String updatedAt;

}
