package com.example.cardgameapi.service;

import com.example.cardgameapi.entity.user.User;
import com.example.cardgameapi.web.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findById(Long id);

    User update(UserDto userDto, Long id);

}
