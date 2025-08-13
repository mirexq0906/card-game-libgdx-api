package com.example.cardgameapi.web.mapper;

import com.example.cardgameapi.entity.user.User;
import com.example.cardgameapi.web.dto.UserDto;
import com.example.cardgameapi.web.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User requestToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public User requestToUser(UserDto userDto, Long id) {
        User user = this.requestToUser(userDto);
        user.setId(id);
        return user;
    }

    public UserResponse userToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole().toString());
        userResponse.setCreatedAt(user.getCreateTime().toString());
        userResponse.setUpdatedAt(user.getUpdateTime().toString());
        return userResponse;
    }

}
