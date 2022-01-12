package com.example.discoveryclient.service;

import com.example.discoveryclient.dto.FirstPageDto;
import com.example.discoveryclient.dto.SecondPageDto;
import com.example.discoveryclient.dto.ThirdPageDto;
import com.example.discoveryclient.model.User;
import com.example.discoveryclient.sequrity.jwt.JwtUtils;

import java.util.NoSuchElementException;

public interface PageService {
    void saveUserFirstPage(JwtUtils jwtUtils, String token, FirstPageDto dto);

    void saveUserSecondPage(JwtUtils jwtUtils, String token, SecondPageDto dto);

    void saveUserThirdPage(JwtUtils jwtUtils, String token, ThirdPageDto dto);

    void setUserId(String phoneNumber);

    Long getIdByPhoneNumber(String phoneNumber) throws NoSuchElementException;

    User getUserById(Long id);
}
