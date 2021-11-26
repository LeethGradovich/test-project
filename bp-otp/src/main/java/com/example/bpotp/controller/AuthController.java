package com.example.bpotp.controller;

import com.example.bpotp.payload.PhoneRequest;
import com.example.bpotp.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Description(value = "Resource for generating and validating OTP requests.")
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/phoneNumber")
    public ResponseEntity<?> registerUser(@Valid @RequestBody PhoneRequest phoneRequest){

        Map<String, String> response = new HashMap<>(2);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}
