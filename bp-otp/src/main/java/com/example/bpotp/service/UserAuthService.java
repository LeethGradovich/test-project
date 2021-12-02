package com.example.bpotp.service;

import com.example.bpotp.model.UserAuth;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public interface UserAuthService {
    public Optional<UserAuth> findByPhoneNumber(String phoneNumber);
}
