package com.example.bpotp.controller;

import com.example.bpotp.dto.request.LoginDto;
import com.example.bpotp.dto.request.PhoneDto;
import com.example.bpotp.dto.response.JwtDto;
import com.example.bpotp.dto.response.MessageDto;
import com.example.bpotp.repository.UserAuthRepository;
import com.example.bpotp.sequrity.jwt.JwtUtils;
import com.example.bpotp.service.OtpService;
import com.example.bpotp.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserAuthRepository userRepository;
    private final OtpService otpService;
    private final JwtUtils jwtUtils;

    @PostMapping("/otp")
    public MessageDto generateOtp(@Valid @RequestBody PhoneDto phoneRequest) throws NoSuchAlgorithmException {
        new AuthServiceImpl(phoneRequest).generateOtp(userRepository, otpService);
        return new MessageDto("OTP successfully generated");
    }

    @PostMapping("/validate")
    public JwtDto validateOtp(@Valid @RequestBody LoginDto loginRequest) {
        val authService = new AuthServiceImpl(loginRequest);
        authService.validateOtp(userRepository, otpService);
        return new JwtDto(authService.getJwt(jwtUtils, authService.authenticate(authenticationManager)),
                authService.authenticate(authenticationManager).getPrincipal().toString());
    }
}
