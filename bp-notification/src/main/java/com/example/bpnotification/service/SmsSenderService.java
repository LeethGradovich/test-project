package com.example.bpnotification.service;

import org.springframework.stereotype.Service;

@Service
public interface SmsSenderService {
    void sendSMS(String userPhone, MessageService messageService);

    String getText(MessageService messageService);
}
