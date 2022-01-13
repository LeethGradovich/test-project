package com.example.discoveryclient.service;

import com.example.discoveryclient.dto.FirstPageDto;
import com.example.discoveryclient.dto.SecondPageDto;
import com.example.discoveryclient.dto.ThirdPageDto;
import com.example.discoveryclient.model.User;

import java.util.NoSuchElementException;

public interface PageService {
    void saveUserFirstPage(String token, FirstPageDto dto);

    void saveUserSecondPage(String token, SecondPageDto dto);

    void saveUserThirdPage(String token, ThirdPageDto dto);

    void setUserId(String phoneNumber);

    Long getIdByPhoneNumber(String phoneNumber) throws NoSuchElementException;

    User getUserById(Long id);
}
