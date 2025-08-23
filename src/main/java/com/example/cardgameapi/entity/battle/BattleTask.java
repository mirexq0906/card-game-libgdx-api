package com.example.cardgameapi.entity.battle;

import com.example.cardgameapi.entity.daily_task.Reward;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class BattleTask {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "battle_mode_id")
    private BattleMode battleMode;

    @ManyToMany
    @JoinTable(
        name = "battle_task_enemy",
        joinColumns = @JoinColumn(name = "battle_task_id"),
        inverseJoinColumns = @JoinColumn(name = "enemy_id")
    )
    private List<Enemy> enemies;

    @ManyToOne
    @JoinColumn(name = "reward_id")
    private Reward reward;

}
