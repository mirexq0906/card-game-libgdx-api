package com.example.cardgameapi.repository;

import com.example.cardgameapi.entity.battle.UserBattleMode;
import com.example.cardgameapi.entity.battle.UserTaskProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBattleModeRepository extends JpaRepository<UserBattleMode, Long> {

    Optional<UserBattleMode> findByUserIdAndBattleModeId(Long userId, Long battleModeId);

}
