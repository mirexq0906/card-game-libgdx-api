package com.example.cardgameapi.service.impl;

import com.example.cardgameapi.entity.character.CharacterInstance;
import com.example.cardgameapi.entity.user.User;
import com.example.cardgameapi.exception.EntityNotFoundException;
import com.example.cardgameapi.repository.CharacterInstanceRepository;
import com.example.cardgameapi.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

    private final CharacterInstanceRepository characterInstanceRepository;

    @Override
    public CharacterInstance findCharacterInstanceById(Long id) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return characterInstanceRepository.findCharacterForUser(principal.getId(), id)
            .orElseThrow(() -> new EntityNotFoundException("Character instance id " + id + " not found"));
    }

}
