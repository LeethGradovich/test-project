package com.example.bpotp.service;

import javax.naming.AuthenticationException;

public class PhoneNumberNotFoundException extends AuthenticationException {
    public PhoneNumberNotFoundException(final String s) {
        super(s);
    }
}
