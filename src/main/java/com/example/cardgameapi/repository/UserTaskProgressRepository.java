package com.example.cardgameapi.repository;

import com.example.cardgameapi.entity.battle.UserTaskProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserTaskProgressRepository extends JpaRepository<UserTaskProgress, Long> {

    List<UserTaskProgress> findAllByUserId(Long userId);

}
