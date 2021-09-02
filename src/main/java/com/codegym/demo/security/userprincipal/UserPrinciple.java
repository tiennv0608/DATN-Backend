package com.codegym.demo.security.userprincipal;

import com.codegym.demo.model.Company;
import com.codegym.demo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrinciple implements UserDetails {
    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> types;

    public UserPrinciple() {
    }

    public UserPrinciple(Long id, String name, String email, String password, Collection<? extends GrantedAuthority> types) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.types = types;
    }

    public static UserDetails buildUser(User user) {
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getType());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        return new UserPrinciple(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }

    public static UserDetails buildCompany(Company company) {
        GrantedAuthority authority = new SimpleGrantedAuthority(company.getType());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        return new UserPrinciple(
                company.getId(),
                company.getCompanyName(),
                company.getEmail(),
                company.getPassword(),
                authorities
        );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return types;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
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
}
