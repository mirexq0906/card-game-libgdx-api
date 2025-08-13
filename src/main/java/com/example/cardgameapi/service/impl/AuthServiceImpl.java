package com.example.cardgameapi.service.impl;

import com.example.cardgameapi.entity.user.User;
import com.example.cardgameapi.repository.UserRepository;
import com.example.cardgameapi.service.AuthService;
import com.example.cardgameapi.service.JwtService;
import com.example.cardgameapi.web.dto.UserDto;
import com.example.cardgameapi.web.mapper.UserMapper;
import com.example.cardgameapi.web.response.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public JwtResponse login(UserDto userDto) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getUsername(),
                        userDto.getPassword()
                )
        );

        User user = (User) authenticate.getPrincipal();

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setAccessToken(jwtService.generateToken(user));
        jwtResponse.setRefreshToken(jwtService.generateRefreshToken(user));
        return jwtResponse;
    }

    @Override
    public User register(UserDto userDto) {
        User user = userMapper.requestToUser(userDto);
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return userRepository.create(user);
    }

}