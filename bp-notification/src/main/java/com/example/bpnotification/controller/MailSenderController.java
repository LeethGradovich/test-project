package com.example.bpnotification.controller;

import com.example.bpnotification.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/emails")
public class MailSenderController {
    private final MailSenderService mailSenderService;

    @GetMapping("/{email}")
    public void sendEmailMessage(@PathVariable String email) {
        mailSenderService.sendMessage(email);
    }
}
