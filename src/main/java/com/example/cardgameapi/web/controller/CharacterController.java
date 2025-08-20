package com.example.cardgameapi.web.controller;

import com.example.cardgameapi.entity.character.CharacterInstance;
import com.example.cardgameapi.service.CharacterService;
import com.example.cardgameapi.service.impl.MergeServiceImpl;
import com.example.cardgameapi.web.dto.MergeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/character")
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;
    private final MergeServiceImpl mergeService;

    @GetMapping("/{id}")
    public CharacterInstance findById(@PathVariable Long id) {
        return characterService.findCharacterInstanceById(id);
    }

    @PostMapping("/merge")
    public CharacterInstance mergeCharacters(@RequestBody MergeDto mergeDto) {
        return mergeService.mergeCharacters(mergeDto);
    }

}
