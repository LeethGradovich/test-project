package com.example.bpotp.service;

import java.security.NoSuchAlgorithmException;

public interface OtpService {
    Boolean generateOtp(String key) throws NoSuchAlgorithmException;

    Boolean validateOTP(String key, Integer otpNumber);
}
