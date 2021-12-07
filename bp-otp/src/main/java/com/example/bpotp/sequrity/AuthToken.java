package com.example.bpotp.sequrity;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AuthToken extends AbstractAuthenticationToken {
    private final Object phoneNumber;
    private final Object code;

    public AuthToken(Object phoneNumber, Object code) {
        super(null);
        this.phoneNumber = phoneNumber;
        this.code = code;
    }

    @Override
    public Object getCredentials() {
        return this.code;
    }

    @Override
    public Object getPrincipal() {
        return this.phoneNumber;
    }
}
