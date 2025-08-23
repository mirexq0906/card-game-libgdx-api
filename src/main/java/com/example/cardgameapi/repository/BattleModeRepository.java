package com.example.cardgameapi.repository;

import com.example.cardgameapi.entity.battle.BattleMode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BattleModeRepository extends JpaRepository<BattleMode, Long> {
}
