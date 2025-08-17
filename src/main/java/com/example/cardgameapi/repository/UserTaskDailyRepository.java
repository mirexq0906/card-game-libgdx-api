package com.example.cardgameapi.repository;

import com.example.cardgameapi.entity.daily_task.UserTaskDaily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserTaskDailyRepository extends JpaRepository<UserTaskDaily, Long> {

    Optional<UserTaskDaily> findByIdAndUserId(Long userTaskDailyId, Long userId);

    List<UserTaskDaily> findAllByUserId(Long userId);

}
