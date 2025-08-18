package com.example.cardgameapi.repository;

import com.example.cardgameapi.entity.character.CharacterInstance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterInstanceRepository extends JpaRepository<CharacterInstance, Long> {

    @Query("SELECT c FROM User u JOIN u.characterInstances c WHERE u.id = :userId AND c.id = :charId")
    Optional<CharacterInstance> findCharacterForUser(@Param("userId") Long userId,
                                                     @Param("charId") Long charId);

}
