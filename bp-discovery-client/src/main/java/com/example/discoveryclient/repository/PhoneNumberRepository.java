package com.example.discoveryclient.repository;

import com.example.discoveryclient.model.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Long> {
    Optional<PhoneNumber> findByPhoneNumber(String phoneNumber);

    boolean existsPhoneNumberByPhoneNumber(String phoneNumber);
}
