package com.example.bpotp.service;

import java.security.NoSuchAlgorithmException;

public interface OtpGenerator {
    Integer generateOTP(String key) throws NoSuchAlgorithmException;

    Integer getOPTByKey(String key);

    void clearOTPFromCache(String key);

}
