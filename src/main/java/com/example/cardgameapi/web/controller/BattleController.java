package com.example.cardgameapi.web.controller;

import com.example.cardgameapi.entity.battle.BattleMode;
import com.example.cardgameapi.entity.battle.BattleTask;
import com.example.cardgameapi.entity.battle.UserBattleMode;
import com.example.cardgameapi.entity.battle.UserTaskProgress;
import com.example.cardgameapi.entity.battle.battle_logic.BattleSession;
import com.example.cardgameapi.service.impl.BattleLogicServiceImpl;
import com.example.cardgameapi.service.impl.BattleServiceImpl;
import com.example.cardgameapi.web.dto.BattleDto;
import com.example.cardgameapi.web.response.BattleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/battle")
public class BattleController {

    private final BattleServiceImpl battleService;
    private final BattleLogicServiceImpl battleLogicService;

    @PostMapping("/battle-logic")
    public BattleResponse createBattleSession(@RequestBody BattleDto battleDto) {
        BattleResponse battleResponse = new BattleResponse();
        battleResponse.setSessionId(battleLogicService.createBattleSession(battleDto).toString());
        return battleResponse;
    }

    @GetMapping("/battle-logic/{sessionId}")
    public BattleSession getBattleSession(@PathVariable UUID sessionId) {
        return battleLogicService.getBattleSession(sessionId);
    }

    @PostMapping("/battle-logic/move-turn")
    public BattleSession makeMoveBattleSession(@RequestBody BattleDto battleDto) {
        return battleLogicService.makeMove(battleDto);
    }

    @PostMapping("/battle-logic/finish")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finishBattleSession(@RequestBody BattleDto battleDto) {
        battleLogicService.finishBattleSession(battleDto);
    }

    @GetMapping("/battle-mode/{battleModeId}/task")
    public List<BattleTask> getBattleTasksByBattleModeId(@PathVariable Long battleModeId) {
        return battleService.getBattleTasksByBattleModeId(battleModeId);
    }

    @GetMapping("/user-task-progress")
    public List<UserTaskProgress> getUserTaskProgressList() {
        return battleService.getUserTaskProgressList();
    }

    @GetMapping("/mode")
    public List<BattleMode> getBattleModes() {
        return battleService.getBattleModes();
    }

    @GetMapping("/user-battle-mode/{battleModeId}")
    public UserBattleMode getOrCreateUserBattleMode(@PathVariable Long battleModeId) {
        return battleService.getOrCreateUserBattleBode(battleModeId);
    }

    @PostMapping("/user-battle-mode/{userBattleModeId}/character/{characterId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addCharacterToUserBattleMode(@PathVariable Long userBattleModeId, @PathVariable Long characterId) {
        battleService.addCharacterToUserBattleMode(characterId, userBattleModeId);
    }

    @DeleteMapping("/user-battle-mode/{userBattleModeId}/character/{characterId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCharacterFromUserBattleMode(@PathVariable Long userBattleModeId, @PathVariable Long characterId) {
        battleService.removeCharacterFromUserBattleMode(characterId, userBattleModeId);
    }

}
