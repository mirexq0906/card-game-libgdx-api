package com.example.cardgameapi.entity.user;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@FieldNameConstants
public class User implements UserDetails {

    private Long id;
    private String username;
    private String email;
    private String password;
    private Role role = Role.USER;
    private Integer gold;
    private Integer mana;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
    }

}
