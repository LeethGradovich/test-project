package com.example.bpotp.sequrity.jwt;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    public final static String AUTHORIZATION_HEADER = "Authorization";
    public final static String AUTHORIZATION_TOKEN = "access_token";
    private final JwtUtils jwtUtils;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        val customFilter = new JwtTokenFilter(jwtUtils);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
