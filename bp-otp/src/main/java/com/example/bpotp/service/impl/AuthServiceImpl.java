package com.example.bpotp.service.impl;

import com.example.bpotp.model.UserAuth;
import com.example.bpotp.dto.request.LoginDTO;
import com.example.bpotp.dto.request.PhoneDTO;
import com.example.bpotp.repository.UserAuthRepository;
import com.example.bpotp.sequrity.AuthToken;
import com.example.bpotp.sequrity.jwt.JwtUtils;
import com.example.bpotp.service.AuthService;
import com.example.bpotp.service.OtpService;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

public class AuthServiceImpl implements AuthService {
    private final PhoneDTO phoneRequest;
    private final LoginDTO loginRequest;
    private final String phoneNumber;

    public AuthServiceImpl(PhoneDTO phoneRequest) {
        this.phoneRequest = phoneRequest;
        this.loginRequest = null;
        this.phoneNumber = phoneRequest.getPhoneNumber();
    }

    public AuthServiceImpl(LoginDTO loginRequest) {
        this.phoneRequest = null;
        this.loginRequest = loginRequest;
        this.phoneNumber = loginRequest.getPhoneNumber();
    }

    public void writeUserToDatabase(UserAuthRepository userRepository) {
        if (!userRepository.existsByPhoneNumber(phoneNumber)) {
            UserAuth user = new UserAuth(phoneRequest.getPhoneNumber());
            userRepository.save(user);
        }
    }

    public void generateAuthOtp(OtpService otpServiceImpl) throws NoSuchAlgorithmException {
        if (!otpServiceImpl.generateOtp(phoneNumber)) {
            throw new NoSuchAlgorithmException();
        }
    }

    public void findUserByPhoneNumber(UserAuthRepository userRepository) throws NoSuchElementException {
        if (!userRepository.existsByPhoneNumber(loginRequest.getPhoneNumber())) {
            throw new NoSuchElementException("Phone number can not be found");
        }
    }

    public void validateUserOtp(OtpService otpServiceImpl) {
        if (!otpServiceImpl.validateOTP(phoneNumber, loginRequest.getCode())) {
            throw new SecurityException("OTP is not valid!");
        }
    }

    public Authentication authenticate(AuthenticationManager authenticationManager) {
        val authToken = new AuthToken(loginRequest.getPhoneNumber(), loginRequest.getCode());
        val authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    public String getJwt(JwtUtils jwtUtils, Authentication authentication) {
        return jwtUtils.generateJwtToken(authentication);
    }
}
