package com.example.cardgameapi.web.controller;

import com.example.cardgameapi.entity.character.CharacterInstance;
import com.example.cardgameapi.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/character")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/{id}")
    public CharacterInstance findById(@PathVariable Long id) {
        return characterService.findCharacterInstanceById(id);
    }

}
