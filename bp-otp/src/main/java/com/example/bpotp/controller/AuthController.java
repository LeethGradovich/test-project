package com.example.bpotp.controller;

import com.example.bpotp.payload.request.LoginRequest;
import com.example.bpotp.payload.request.PhoneRequest;
import com.example.bpotp.payload.response.JwtResponse;
import com.example.bpotp.payload.response.MessageResponse;
import com.example.bpotp.repository.UserAuthRepository;
import com.example.bpotp.sequrity.jwt.JwtUtils;
import com.example.bpotp.service.AuthServiceImpl;
import com.example.bpotp.service.OtpServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserAuthRepository userRepository;
    private final OtpServiceImpl otpServiceImpl;
    private final JwtUtils jwtUtils;

    @PostMapping("/otp")
    public MessageResponse generateOtp(@Valid @RequestBody PhoneRequest phoneRequest) throws NoSuchAlgorithmException {
        AuthServiceImpl authService = new AuthServiceImpl(phoneRequest);
        authService.writeUserToDatabase(userRepository);
        authService.generateAuthOtp(otpServiceImpl);
        return new MessageResponse("OTP successfully generated");
    }

    @PostMapping("/validate")
    public JwtResponse validateOtp(@Valid @RequestBody LoginRequest loginRequest) {
        final AuthServiceImpl authService = new AuthServiceImpl(loginRequest);
        authService.findUserByPhoneNumber(userRepository);
        authService.validateUserOtp(otpServiceImpl);
        final Authentication authentication = authService.authenticate(authenticationManager);
        return new JwtResponse(authService.getJwt(jwtUtils, authentication), authentication.getPrincipal().toString());
    }

    @ExceptionHandler(NoSuchAlgorithmException.class)
    public ResponseEntity<?> handleNoSuchAlgorithmException(NoSuchAlgorithmException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<?> handleSecurityException(SecurityException ex) {
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
}
