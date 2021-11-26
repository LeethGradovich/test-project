package com.example.bpotp.repository;

import com.example.bpotp.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    UserAuth findByPhoneNumber(String phoneNumber);
}
