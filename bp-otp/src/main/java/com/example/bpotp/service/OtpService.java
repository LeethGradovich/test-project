package com.example.bpotp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Description(value = "Service responsible for handling OTP related functionality.")
@Service
public class OtpService {
    private final Logger LOGGER = LoggerFactory.getLogger(OtpService.class);

    @Autowired
    private OtpGenerator otpGenerator;

    public Boolean generateOtp(String key) throws NoSuchAlgorithmException {
        Integer otpValue = otpGenerator.generateOTP(key);
        if (otpValue == -1) {
            LOGGER.error("OTP generator is not working...");
            return  false;
        }
        LOGGER.info("Generated OTP: {}", otpValue);
        return true;
    }

    public Boolean validateOTP(String key, Integer otpNumber) {
        Integer cacheOTP = otpGenerator.getOPTByKey(key);
        if (cacheOTP!=null && cacheOTP.equals(otpNumber)) {
            otpGenerator.clearOTPFromCache(key);
            return true;
        }
        return false;
    }
}
