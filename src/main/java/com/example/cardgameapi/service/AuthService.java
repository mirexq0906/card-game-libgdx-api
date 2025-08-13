package com.example.cardgameapi.service;


import com.example.cardgameapi.entity.user.User;
import com.example.cardgameapi.web.dto.UserDto;
import com.example.cardgameapi.web.response.JwtResponse;

public interface AuthService {

    JwtResponse login(UserDto userDto);

    User register(UserDto userDto);

}
