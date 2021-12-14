package com.example.bpotp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Description(value = "Service responsible for handling OTP related functionality.")
@Service
@Slf4j
@RequiredArgsConstructor
public class OtpServiceImpl implements OtpService {
    private final OtpGeneratorImpl otpGeneratorImpl;

    public Boolean generateOtp(String key) throws NoSuchAlgorithmException {
        Integer otpValue = otpGeneratorImpl.generateOTP(key);
        if (otpValue == -1) {
            log.error("OTP generator is not working...");
            return false;
        }
        log.info("Generated OTP: {}", otpValue);
        return true;
    }

    public Boolean validateOTP(String key, Integer otpNumber) {
        Integer cacheOTP = otpGeneratorImpl.getOPTByKey(key);
        if (cacheOTP != null && cacheOTP.equals(otpNumber)) {
            otpGeneratorImpl.clearOTPFromCache(key);
            return true;
        }
        return false;
    }
}
