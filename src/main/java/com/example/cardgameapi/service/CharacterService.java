package com.example.cardgameapi.service;

import com.example.cardgameapi.entity.character.CharacterInstance;

public interface CharacterService {

    CharacterInstance findCharacterInstanceById(Long id);

}
