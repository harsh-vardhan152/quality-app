package com.harshvardhan.quality_app.service;

import com.harshvardhan.quality_app.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {

    private final User user;

    public UserDetailsImpl(User user){
        this.user=user;
    }
    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return user.
                getRoles()
                .stream()
                .map(role->new SimpleGrantedAuthority("ROLE_" + role.getName().name()))
                .collect(Collectors.toSet());
    }

    /**
     * @return
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * @return
     */
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * @return
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
