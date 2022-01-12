package com.example.discoveryclient.service.impl;

import com.example.discoveryclient.dto.FirstPageDto;
import com.example.discoveryclient.dto.SecondPageDto;
import com.example.discoveryclient.dto.ThirdPageDto;
import com.example.discoveryclient.model.User;
import com.example.discoveryclient.repository.PhoneNumberRepository;
import com.example.discoveryclient.repository.UserRepository;
import com.example.discoveryclient.sequrity.jwt.JwtUtils;
import com.example.discoveryclient.service.PageService;
import com.example.discoveryclient.service.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PageServiceImpl implements PageService {

    private final UserRepository userRepository;
    private final PhoneNumberRepository phoneNumberRepository;
    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    public void saveUserFirstPage(JwtUtils jwtUtils, String token, FirstPageDto dto) {
        val phoneNumber = jwtUtils.getPhoneNumberFromJwtToken(token);
        setUserId(phoneNumber);
        val user = mapper.firstPageDtoToUser(dto, getUserById(getIdByPhoneNumber(phoneNumber)));
        userRepository.save(user);
    }

    public void saveUserSecondPage(JwtUtils jwtUtils, String token, SecondPageDto dto) {
        val phoneNumber = jwtUtils.getPhoneNumberFromJwtToken(token);
        val user = mapper.secondPageDtoToUser(dto, getUserById(getIdByPhoneNumber(phoneNumber)));
        userRepository.save(user);
    }

    public void saveUserThirdPage(JwtUtils jwtUtils, String token, ThirdPageDto dto) {
        val phoneNumber = jwtUtils.getPhoneNumberFromJwtToken(token);
        val user = mapper.thirdPageDtoToUser(dto, getUserById(getIdByPhoneNumber(phoneNumber)));
        userRepository.save(user);
    }

    public void setUserId(String phoneNumber) {
        if (getUserById(getIdByPhoneNumber(phoneNumber)) == null) {
            val user = new User();
            user.setId(getIdByPhoneNumber(phoneNumber));
            userRepository.save(user);
        }
    }

    public Long getIdByPhoneNumber(String phoneNumber) throws NoSuchElementException {
        if (!phoneNumberRepository.existsPhoneNumberByPhoneNumber(phoneNumber)) {
            throw new NoSuchElementException("Phone number can not be found");
        }
        return phoneNumberRepository.findByPhoneNumber(phoneNumber).orElseThrow(NoSuchElementException::new).getId();
    }

    public User getUserById(Long id) throws NoSuchElementException {
        if (!userRepository.existsById(id)) {
            throw new NoSuchElementException("User can not be found");
        }
        return userRepository.findUserById(id).orElseThrow(NoSuchElementException::new);
    }
}

