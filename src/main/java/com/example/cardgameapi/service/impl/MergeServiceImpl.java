package com.example.cardgameapi.service.impl;

import com.example.cardgameapi.entity.character.CharacterInstance;
import com.example.cardgameapi.entity.character.RarityType;
import com.example.cardgameapi.entity.user.User;
import com.example.cardgameapi.exception.EntityNotFoundException;
import com.example.cardgameapi.repository.CharacterInstanceRepository;
import com.example.cardgameapi.repository.UserRepository;
import com.example.cardgameapi.service.UserService;
import com.example.cardgameapi.web.dto.MergeDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MergeServiceImpl {

    private final CharacterInstanceRepository characterInstanceRepository;
    private final UserService userService;

    @Transactional
    public CharacterInstance mergeCharacters(MergeDto mergeDto) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findById(principal.getId());
        CharacterInstance characterInstance1 = characterInstanceRepository.findCharacterForUser(
            principal.getId(),
            mergeDto.getTargetCharacterId()
        ).orElseThrow(() -> new EntityNotFoundException("Character instance not found"));
        CharacterInstance characterInstance2 = characterInstanceRepository.findCharacterForUser(
            principal.getId(),
            mergeDto.getFoodCharacterId()
        ).orElseThrow(() -> new EntityNotFoundException("Character instance not found"));

        if (!characterInstance1.getFractionType().equals(characterInstance2.getFractionType())) {
            throw new IllegalArgumentException("Character instances do not match");
        }

        RarityType higherRarity = getHigherRarity(characterInstance1.getRarityType(), characterInstance2.getRarityType());
        CharacterInstance newChar = new CharacterInstance();
        newChar.setLevel(1);
        newChar.setTemplate(characterInstance1.getTemplate());
        newChar.setRarityType(higherRarity);
        newChar.setFractionType(characterInstance1.getFractionType());
        newChar.setUser(user);

        characterInstanceRepository.delete(characterInstance1);
        characterInstanceRepository.delete(characterInstance2);

        return characterInstanceRepository.save(newChar);
    }

    private RarityType getHigherRarity(RarityType r1, RarityType r2) {
        RarityType higherRarity = null;

        switch (r1) {
            case UNCOMMON -> higherRarity = RarityType.RARE;
            case RARE -> higherRarity = RarityType.EPIC;
            case EPIC -> higherRarity = RarityType.LEGEND;
            case LEGEND -> higherRarity = RarityType.LEGEND;
        }

        return higherRarity;
    }

}
