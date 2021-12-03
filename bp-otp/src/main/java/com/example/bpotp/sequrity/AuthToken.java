package com.example.bpotp.sequrity;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AuthToken extends AbstractAuthenticationToken {
    private Object phoneNumber;
    private Object code;

    public AuthToken(Object phoneNumber, Object code){
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

    public void setPhoneNumber(Object phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCode(Object code) {
        this.code = code;
    }
}
