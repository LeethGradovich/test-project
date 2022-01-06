package com.example.bpotp.service.impl;

import com.example.bpotp.service.OtpService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@Description(value = "Service responsible for handling OTP related functionality.")
@Slf4j
@Service
public class OtpServiceImpl implements OtpService {
    private final LoadingCache<String, Integer> otpCache;
    private final long EXPIRE_MIN = 1;
    private final int GENERATOR_VALUE = 900000;
    private final int BASE_VALUE = 100000;

    public OtpServiceImpl() {
        this.otpCache = generateCache();
    }

    public Boolean generateOtp(String key) throws NoSuchAlgorithmException {
        val otpValue = createOTP(key);
        if (otpValue == -1) {
            log.error("OTP generator is not working...");
            return false;
        }
        log.info("Generated OTP: {}", otpValue);
        return true;
    }

    public Boolean validateOTP(String key, Integer otpNumber) {
        val cacheOTP = getOTPByKey(key);
        if (cacheOTP != null && cacheOTP.equals(otpNumber)) {
            clearOTPFromCache(key);
            return true;
        }
        return false;
    }

    public LoadingCache<String, Integer> generateCache() {
        return CacheBuilder.newBuilder()
                .expireAfterWrite(EXPIRE_MIN, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String s) {
                        return 0;
                    }
                });
    }

    public Integer createOTP(String key) throws NoSuchAlgorithmException {
        val secureRandom = SecureRandom.getInstance("SHA1PRNG");
        val OTP = BASE_VALUE + secureRandom.nextInt(GENERATOR_VALUE);
        otpCache.put(key, OTP);
        return OTP;
    }

    public Integer getOTPByKey(String key) {
        return otpCache.getIfPresent(key);
    }

    public void clearOTPFromCache(String key) {
        otpCache.invalidate(key);
    }
}
