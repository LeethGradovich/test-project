package com.example.bpotp.service;

import com.example.bpotp.model.UserAuth;
import com.example.bpotp.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    UserAuthRepository userAuthRepository;

    @Override
    public UserAuth findByPhoneNumber(String phoneNumber) {
        return userAuthRepository.findByPhoneNumber(phoneNumber);
    }
}