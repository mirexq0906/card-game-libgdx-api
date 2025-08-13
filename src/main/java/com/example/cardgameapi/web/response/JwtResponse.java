package com.example.cardgameapi.web.response;

import lombok.Data;

@Data
public class JwtResponse {

    private String accessToken;

    private String refreshToken;

}
