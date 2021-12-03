package com.example.bpotp.controller;

import com.example.bpotp.model.UserAuth;
import com.example.bpotp.payload.request.LoginRequest;
import com.example.bpotp.payload.request.PhoneRequest;
import com.example.bpotp.payload.response.JwtResponse;
import com.example.bpotp.payload.response.MessageResponse;
import com.example.bpotp.repository.UserAuthRepository;
import com.example.bpotp.sequrity.AuthToken;
import com.example.bpotp.sequrity.jwt.JwtUtils;
import com.example.bpotp.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserAuthRepository userRepository;

    @Autowired
    OtpService otpService;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/otp")
    public ResponseEntity<?> generateOtp(@Valid @RequestBody PhoneRequest phoneRequest) throws NoSuchAlgorithmException {

        String phoneNumber = phoneRequest.getPhoneNumber();

        if (!userRepository.existsByPhoneNumber(phoneRequest.getPhoneNumber())) {
            UserAuth user = new UserAuth(phoneRequest.getPhoneNumber());
            userRepository.save(user);
        }

        if (!otpService.generateOtp(phoneNumber))
        {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: OTP can not be generated."));
        }

        return ResponseEntity
                .ok()
                .body("OTP successfully generated.");
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateOtp(@Valid @RequestBody LoginRequest loginRequest) {

        String phoneNumber = loginRequest.getPhoneNumber();
        if (!userRepository.existsByPhoneNumber(loginRequest.getPhoneNumber())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: your phone number can not be found."));
        }
        int code = loginRequest.getCode();

        if (!otpService.validateOTP(phoneNumber, code)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: OTP is not valid!"));
        }

        Authentication authentication = authenticationManager.authenticate(new AuthToken(loginRequest.getPhoneNumber(), loginRequest.getCode()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity
                .ok(new JwtResponse(jwt,
                        authentication.getPrincipal().toString()));
    }
}
