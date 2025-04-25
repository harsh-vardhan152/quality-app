package com.harshvardhan.quality_app.config;

import com.harshvardhan.quality_app.service.UserDetailsImpl;
import com.harshvardhan.quality_app.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SpringSecurityConfigImpl {

    private final UserDetailsServiceImpl userDetailsService;

    public SpringSecurityConfigImpl(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.
                csrf(customizer->customizer.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sessionManagement->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->auth
                        .requestMatchers("/api/auth/register").permitAll()
                        .requestMatchers("/api/auth/**").hasRole("ADMIN")
                        .requestMatchers("/users/{id}").hasAnyRole("DEVELOPER", "TESTER", "PUBLISHER")
                        .requestMatchers("/api/tasks/**").hasRole("ADMIN")
                        .requestMatchers("/api/tasks","/update-request/{id}", "/{id}/status").hasAnyRole("DEVELOPER","TESTER","PUBLISHER")
                        .anyRequest().authenticated()).build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}