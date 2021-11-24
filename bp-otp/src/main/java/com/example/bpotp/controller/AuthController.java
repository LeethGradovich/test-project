package com.example.bpotp.controller;

import com.example.bpotp.payload.PhoneRequest;
import com.example.bpotp.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

//    @Autowired
//    GeneratorService generatorService;

//    public void main(){
//
//        String OTP = generatorService.generateCode();
//    }


//    @PostMapping("/phoneNumber")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody PhoneRequest phoneRequest){
//
//    }



}
