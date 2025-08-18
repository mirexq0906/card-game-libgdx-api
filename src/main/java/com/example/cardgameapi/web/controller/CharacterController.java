package com.example.cardgameapi.web.controller;

import com.example.cardgameapi.entity.character.CharacterInstance;
import com.example.cardgameapi.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/character")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @PostMapping("/{id}")
    public CharacterInstance findById(@PathVariable Long id) {
        return characterService.findCharacterInstanceById(id);
    }

}
