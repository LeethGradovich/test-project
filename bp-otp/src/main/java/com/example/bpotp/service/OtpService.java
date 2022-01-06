package com.example.bpotp.service;

import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public interface OtpService {
    Boolean generateOtp(String key) throws NoSuchAlgorithmException;

    Boolean validateOTP(String key, Integer otpNumber);
}
