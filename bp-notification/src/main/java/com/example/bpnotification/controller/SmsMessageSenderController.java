package com.example.bpnotification.controller;

import com.example.bpnotification.service.SmsSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sendSMS")
@RequiredArgsConstructor
public class SmsMessageSenderController {
    private final SmsSenderService smsSenderService;

    @GetMapping("/{phone}")
    public void sendMessage(@PathVariable String phone) {
        smsSenderService.sendSMS(phone);
    }
}
