package com.codegym.demo.security.userprincipal;

import com.codegym.demo.model.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class CompanyPrinciple implements UserDetails{
    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> types;

    public CompanyPrinciple() {
    }

    public CompanyPrinciple(Long id, String name, String email, String password, Collection<? extends GrantedAuthority> types) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.types = types;
    }

    public static CompanyPrinciple build(Company company) {
        GrantedAuthority authority = new SimpleGrantedAuthority(company.getType());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        return new CompanyPrinciple(
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
        return false;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
