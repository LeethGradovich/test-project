package com.example.discoveryclient.sequrity.jwt;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component("authProvider")
public class AuthProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return new AuthToken(authentication.getPrincipal(), authentication.getCredentials());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (AuthToken.class.isAssignableFrom(authentication));
    }
}
