package com.harshvardhan.quality_app.service;

import com.harshvardhan.quality_app.entity.User;
import com.harshvardhan.quality_app.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    /**
     * @param username the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user==null){
            log.error("User is not present in the system{}", user);
            throw new UsernameNotFoundException("User is not present in the system");
        }
        return new UserDetailsImpl(user);

    }
}
