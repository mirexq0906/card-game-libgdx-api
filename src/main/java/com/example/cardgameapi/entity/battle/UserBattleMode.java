package com.example.cardgameapi.entity.battle;

import com.example.cardgameapi.entity.character.CharacterInstance;
import com.example.cardgameapi.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class UserBattleMode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "battle_mode_id")
    private BattleMode battleMode;

    @Transient
    private Integer maxCharacters = 3;

    @ManyToMany
    @JoinTable(
        name = "user_battle_mode_character_instance",
        joinColumns = @JoinColumn(name = "user_battle_mode_id"),
        inverseJoinColumns = @JoinColumn(name = "character_instance_id")
    )
    private List<CharacterInstance> characters = new ArrayList<>();


    public void addCharacter(CharacterInstance character) {
        if (characters.size() >= maxCharacters) {
            throw new IllegalStateException("Нельзя добавить больше " + maxCharacters + " персонажей в режим!");
        }
        characters.add(character);
    }

    public void removeCharacterById(Long characterId) {
        characters.removeIf(character -> character.getId().equals(characterId));
    }

}
