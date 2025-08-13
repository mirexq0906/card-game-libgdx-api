package com.example.cardgameapi.service.impl;

import com.example.cardgameapi.entity.user.User;
import com.example.cardgameapi.repository.UserRepository;
import com.example.cardgameapi.service.UserService;
import com.example.cardgameapi.web.dto.UserDto;
import com.example.cardgameapi.web.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User update(UserDto userDto, Long id) {
        User user = userMapper.requestToUser(userDto, id);
        User updatedUser = findById(id);
        Optional.ofNullable(user.getUsername()).ifPresent(updatedUser::setUsername);
        Optional.ofNullable(user.getPassword()).ifPresent((item) -> {
            updatedUser.setPassword(passwordEncoder.encode(item));
        });
        Optional.ofNullable(user.getEmail()).ifPresent(updatedUser::setEmail);

        return userRepository.update(updatedUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}