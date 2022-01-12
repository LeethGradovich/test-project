package com.example.discoveryclient.controllers;

import com.example.discoveryclient.dto.FirstPageDto;
import com.example.discoveryclient.dto.SecondPageDto;
import com.example.discoveryclient.dto.ThirdPageDto;
import com.example.discoveryclient.sequrity.jwt.JwtUtils;
import com.example.discoveryclient.service.PageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/pages")
@RequiredArgsConstructor
public class UserDataController {
    private final PageService pageService;
    private final JwtUtils jwtUtils;

    @PutMapping("/first-page")
    public void fillInFirstPage(@RequestHeader(value = "Authorization") String token, @Valid @RequestBody FirstPageDto firstPageDto) {
        pageService.saveUserFirstPage(jwtUtils, token, firstPageDto);
    }

    @PutMapping("/second-page")
    public void fillInSecondPage(@RequestHeader(value = "Authorization") String token, @Valid @RequestBody SecondPageDto secondPageDto) {
        pageService.saveUserSecondPage(jwtUtils, token, secondPageDto);
    }

    @PutMapping("/third-page")
    public void fillInThirdPage(@RequestHeader(value = "Authorization") String token, @Valid @RequestBody ThirdPageDto thirdPageDto) {
        pageService.saveUserThirdPage(jwtUtils, token, thirdPageDto);
    }
}
