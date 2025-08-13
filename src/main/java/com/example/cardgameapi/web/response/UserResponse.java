package com.example.cardgameapi.web.response;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;

    private String username;

    private String email;

    private String role;

    private String createdAt;

    private String updatedAt;

}
