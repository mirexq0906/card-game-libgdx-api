package com.example.cardgameapi.entity.battle;

import com.example.cardgameapi.entity.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserTaskProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "battle_task_id")
    private BattleTask battleTask;

    @Enumerated(EnumType.STRING)
    private ProgressType progressType = ProgressType.PROCESSING;

}
