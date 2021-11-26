package com.example.bpotp.service;

import com.example.bpotp.model.UserAuth;
import org.springframework.stereotype.Service;

@Service
public interface UserAuthService {
    public UserAuth findByPhoneNumber(String phoneNumber);
}
