package com.codegym.demo.security.principal;

import com.codegym.demo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserPrinciple implements UserDetails {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> channel;

    public UserPrinciple(Long id, String username, String password, Collection<? extends GrantedAuthority> channel) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.channel = channel;
    }

    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getType());
        authorities.add(authority);
        return new UserPrinciple(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return channel;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
