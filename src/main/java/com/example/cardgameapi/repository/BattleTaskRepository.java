package com.example.cardgameapi.repository;

import com.example.cardgameapi.entity.battle.BattleTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BattleTaskRepository extends JpaRepository<BattleTask, Long> {

    List<BattleTask> findAllByBattleModeId(Long battleModeId);

}
