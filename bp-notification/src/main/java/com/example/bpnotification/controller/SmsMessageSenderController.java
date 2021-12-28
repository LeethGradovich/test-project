package com.example.bpnotification.controller;

import com.example.bpnotification.service.SmsSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms-messages")
@RequiredArgsConstructor
public class SmsMessageSenderController {
    private final SmsSenderService smsSenderService;

    @GetMapping("/{phone-number}")
    public void sendMessage(@PathVariable("phone-number") String phoneNumber) {
        smsSenderService.sendSMS(phoneNumber);
    }
}
