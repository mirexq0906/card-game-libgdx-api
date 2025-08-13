package com.example.cardgameapi.repository;

import com.example.cardgameapi.entity.user.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);

    User create(User user);

    User update(User user);

    Integer count();

}