package com.example.discoveryclient.controllers;

import com.example.discoveryclient.dto.FirstPageDto;
import com.example.discoveryclient.dto.SecondPageDto;
import com.example.discoveryclient.dto.ThirdPageDto;
import com.example.discoveryclient.repository.PhoneNumberRepository;
import com.example.discoveryclient.repository.UserRepository;
import com.example.discoveryclient.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pages")
@RequiredArgsConstructor
public class UserDataController {
    private final PageService pageService;
    private final PhoneNumberRepository phoneNumberRepository;
    private final UserRepository userRepository;

    @PutMapping("/first-page/{phone-number}")
    public void fillInFirstPage(@PathVariable("phone-number") String phoneNumber, @Valid @RequestBody FirstPageDto firstPageDto) {
        pageService.saveUserFirstPage(userRepository, phoneNumberRepository, phoneNumber, firstPageDto);
    }

    @PutMapping("/second-page/{phone-number}")
    public void fillInSecondPage(@PathVariable("phone-number") String phoneNumber, @Valid @RequestBody SecondPageDto secondPageDto) {
        pageService.saveUserSecondPage(userRepository, phoneNumberRepository, phoneNumber, secondPageDto);
    }

    @PutMapping("/third-page/{phone-number}")
    public void fillInThirdPage(@PathVariable("phone-number") String phoneNumber, @Valid @RequestBody ThirdPageDto thirdPageDto) {
        pageService.saveUserThirdPage(userRepository, phoneNumberRepository, phoneNumber, thirdPageDto);
    }
}
