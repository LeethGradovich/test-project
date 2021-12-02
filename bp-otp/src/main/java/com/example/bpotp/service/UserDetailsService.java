package com.example.bpotp.service;

import com.example.bpotp.model.UserAuth;
import com.example.bpotp.repository.UserAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class UserDetailsService {
    @Autowired
    UserAuthRepository userRepository;

    @Transactional
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        UserAuth user = userRepository.findByPhoneNumber( phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " +  phoneNumber));

        return UserDetails.build(user);
    }
}
