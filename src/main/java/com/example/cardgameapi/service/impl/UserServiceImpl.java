package com.example.cardgameapi.service.impl;

import com.example.cardgameapi.entity.collection.Character;
import com.example.cardgameapi.entity.inventory.Inventory;
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
    @Transactional
    public Set<Inventory> getInventoryItems() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(principal.getId())
            .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getInventories();
    }

    @Override
    @Transactional
    public Set<Character> getCollectionItems() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findById(principal.getId())
            .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getCharacters();
    }

}
