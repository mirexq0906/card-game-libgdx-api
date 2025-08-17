package com.example.cardgameapi.repository;

import com.example.cardgameapi.entity.daily_task.DailyTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyTaskRepository extends JpaRepository<DailyTask, Integer> {
}
