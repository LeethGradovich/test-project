package com.example.bpotp.service;

import com.example.bpotp.repository.UserAuthRepository;
import com.example.bpotp.sequrity.jwt.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

public interface AuthService {
    void writeUserToDatabase(UserAuthRepository userRepository);

    void generateAuthOtp(OtpServiceImpl otpServiceImpl) throws NoSuchAlgorithmException;

    void findUserByPhoneNumber(UserAuthRepository userRepository) throws NoSuchElementException;

    void validateUserOtp(OtpServiceImpl otpServiceImpl);

    Authentication authenticate(AuthenticationManager authenticationManager);

    String getJwt(JwtUtils jwtUtils, Authentication authentication);
}
