package com.example.cardgameapi.web.controller;

import com.example.cardgameapi.service.AuthService;
import com.example.cardgameapi.web.dto.UserDto;
import com.example.cardgameapi.web.mapper.UserMapper;
import com.example.cardgameapi.web.response.JwtResponse;
import com.example.cardgameapi.web.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authService;

    private final UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.authService.login(userDto)
        );
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.userMapper.userToResponse(
                        this.authService.register(userDto)
                )
        );
    }

}