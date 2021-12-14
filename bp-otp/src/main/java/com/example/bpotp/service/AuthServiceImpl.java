package com.example.bpotp.service;

import com.example.bpotp.model.UserAuth;
import com.example.bpotp.payload.request.LoginRequest;
import com.example.bpotp.payload.request.PhoneRequest;
import com.example.bpotp.repository.UserAuthRepository;
import com.example.bpotp.sequrity.AuthToken;
import com.example.bpotp.sequrity.jwt.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

@Service
public class AuthServiceImpl implements AuthService {
    private final PhoneRequest phoneRequest;
    private final LoginRequest loginRequest;
    private final String phoneNumber;

    public AuthServiceImpl(PhoneRequest phoneRequest) {
        this.phoneRequest = phoneRequest;
        this.loginRequest = null;
        this.phoneNumber = phoneRequest.getPhoneNumber();
    }

    public AuthServiceImpl(LoginRequest loginRequest) {
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

    public void generateAuthOtp(OtpServiceImpl otpServiceImpl) throws NoSuchAlgorithmException {
        if (!otpServiceImpl.generateOtp(phoneNumber)) {
            throw new NoSuchAlgorithmException();
        }
    }

    public void findUserByPhoneNumber(UserAuthRepository userRepository) throws NoSuchElementException {
        if (!userRepository.existsByPhoneNumber(loginRequest.getPhoneNumber())) {
            throw new NoSuchElementException("Phone number can not be found");
        }
    }

    public void validateUserOtp(OtpServiceImpl otpServiceImpl) {
        if (!otpServiceImpl.validateOTP(phoneNumber, loginRequest.getCode())) {
            throw new SecurityException("OTP is not valid!");
        }
    }

    public Authentication authenticate(AuthenticationManager authenticationManager) {
        final AuthToken authToken = new AuthToken(loginRequest.getPhoneNumber(), loginRequest.getCode());
        final Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    public String getJwt(JwtUtils jwtUtils, Authentication authentication) {
        return jwtUtils.generateJwtToken(authentication);
    }
}
