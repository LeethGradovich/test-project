package com.example.bpotp.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@Description(value = "Service for generating and validating OTP.")
@Service
public class OtpGeneratorImpl implements OtpGenerator {
    private static final Integer EXPIRE_MIN = 1;
    private final LoadingCache<String, Integer> otpCache;

    public OtpGeneratorImpl() {
        otpCache = CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRE_MIN, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String s) throws Exception {
                        return 0;
                    }
                });
    }

    public Integer generateOTP(String key) throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        int OTP = 100000 + sr.nextInt(900000);
        otpCache.put(key, OTP);
        return OTP;
    }

    public Integer getOPTByKey(String key) {
        return otpCache.getIfPresent(key);
    }

    public void clearOTPFromCache(String key) {
        otpCache.invalidate(key);
    }
}
