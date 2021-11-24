package com.example.bpotp.service;

import com.bastiaanjansen.otp.HMACAlgorithm;
import com.bastiaanjansen.otp.SecretGenerator;
import com.bastiaanjansen.otp.TOTPGenerator;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class GeneratorService {
    private TOTPGenerator totp;

    public void setParams(TOTPGenerator.Builder builder) {
        builder
                .withPasswordLength(6)
                .withAlgorithm(HMACAlgorithm.SHA1)
                .withPeriod(Duration.ofSeconds(600));
    }

    public void buildTotp() {
        TOTPGenerator.Builder builder = new TOTPGenerator.Builder(SecretGenerator.generate());
        setParams(builder);
        totp = builder.build();
    }

    public String generateCode() {
        buildTotp();
        return totp.generate();
    }

    public boolean verifyCode(String userCode) {
        boolean isValid = false;
        try {
            isValid = totp.verify(userCode);
        } catch (IllegalStateException e) {
            throw e;
        }
        return isValid;
    }
}
