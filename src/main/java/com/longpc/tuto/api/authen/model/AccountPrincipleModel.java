package com.longpc.tuto.api.authen.model;

import com.longpc.tuto.api.data.entity.authen.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Long PC
 * 28/12/2023| 17:05 | 2023
 **/

public class AccountPrincipleModel extends Account implements UserDetails {


    private Collection<? extends GrantedAuthority> authorities;

    public AccountPrincipleModel(String id, String partyId, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super();
        this.setId(id);
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.partyId = partyId;
    }

    public static AccountPrincipleModel create(Account account) {
        Set<String> roles = account.getRole();
        List<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return new AccountPrincipleModel(account.getId(), account.getPartyId(), account.getUsername(), account.getPassword(), authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
