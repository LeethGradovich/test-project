package com.example.bpnotification.service;

public interface SmsSenderService {
    void sendSMS(String userPhone, MessageService messageService);

    String getText(MessageService messageService);
}
