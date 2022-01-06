package com.example.discoveryclient.service;

import com.example.discoveryclient.dto.FirstPageDto;
import com.example.discoveryclient.dto.SecondPageDto;
import com.example.discoveryclient.dto.ThirdPageDto;
import com.example.discoveryclient.model.User;
import com.example.discoveryclient.repository.PhoneNumberRepository;
import com.example.discoveryclient.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public interface PageService {
    void saveUserFirstPage(UserRepository userRepository,
                           PhoneNumberRepository phoneNumberRepository,
                           String phoneNumber,
                           FirstPageDto dto);

    void saveUserSecondPage(UserRepository userRepository,
                            PhoneNumberRepository phoneNumberRepository,
                            String phoneNumber,
                            SecondPageDto dto);

    void saveUserThirdPage(UserRepository userRepository,
                           PhoneNumberRepository phoneNumberRepository,
                           String phoneNumber,
                           ThirdPageDto dto);

    Long getIdByPhoneNumber(String phoneNumber, PhoneNumberRepository phoneNumberRepository);

    User getUserById(Long id, UserRepository userRepository);
}
