package com.example.bpotp.controller;

import com.example.bpotp.dto.request.LoginDTO;
import com.example.bpotp.dto.request.PhoneDTO;
import com.example.bpotp.dto.response.JwtDTO;
import com.example.bpotp.dto.response.MessageDTO;
import com.example.bpotp.repository.UserAuthRepository;
import com.example.bpotp.sequrity.jwt.JwtUtils;
import com.example.bpotp.service.OtpService;
import com.example.bpotp.service.impl.AuthServiceImpl;
import lombok.RequiredArgsConstructor;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
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
    public MessageDTO generateOtp(@Valid @RequestBody PhoneDTO phoneRequest) throws NoSuchAlgorithmException {
        val authService = new AuthServiceImpl(phoneRequest);
        authService.writeUserToDatabase(userRepository);
        authService.generateAuthOtp(otpService);
        return new MessageDTO("OTP successfully generated");
    }

    @PostMapping("/validate")
    public JwtDTO validateOtp(@Valid @RequestBody LoginDTO loginRequest) {
        val authService = new AuthServiceImpl(loginRequest);
        authService.findUserByPhoneNumber(userRepository);
        authService.validateUserOtp(otpService);
        final Authentication authentication = authService.authenticate(authenticationManager);
        return new JwtDTO(authService.getJwt(jwtUtils, authentication), authentication.getPrincipal().toString());
    }
}
