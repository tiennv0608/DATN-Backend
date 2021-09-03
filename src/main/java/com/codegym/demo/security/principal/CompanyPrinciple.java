package com.codegym.demo.security.principal;

import com.codegym.demo.model.Company;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class CompanyPrinciple implements UserDetails {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> channel;

    public CompanyPrinciple(Long id, String username, String password, Collection<? extends GrantedAuthority> channel) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.channel = channel;
    }

    public static CompanyPrinciple build(Company company) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        GrantedAuthority authority = new SimpleGrantedAuthority(company.getType());
        authorities.add(authority);
        return new CompanyPrinciple(
                company.getId(),
                company.getEmail(),
                company.getPassword(),
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

        CompanyPrinciple merchant = (CompanyPrinciple) o;
        return Objects.equals(id, merchant.id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
