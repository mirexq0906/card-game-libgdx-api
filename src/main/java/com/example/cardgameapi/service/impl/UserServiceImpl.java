package com.example.cardgameapi.service.impl;

import com.example.cardgameapi.entity.character.CharacterInstance;
import com.example.cardgameapi.entity.character.CharacterTemplate;
import com.example.cardgameapi.entity.inventory.Inventory;
import com.example.cardgameapi.entity.inventory.InventoryType;
import com.example.cardgameapi.entity.user.User;
import com.example.cardgameapi.repository.UserRepository;
import com.example.cardgameapi.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    @Transactional
    public Set<Inventory> getInventoryItems(InventoryType inventoryType) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findById(principal.getId()).getInventories().stream()
            .filter(inventory -> inventoryType == null || inventory.getType() == inventoryType)
            .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public List<CharacterInstance> getCharacterInstances() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return findById(principal.getId()).getCharacterInstances();
    }

}
