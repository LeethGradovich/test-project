package com.example.bpnotification.service;

import org.springframework.stereotype.Service;

@Service
public interface MailSenderService {
    void sendMessage(String userEmailAddress, MessageService messageService);

    void setMessage(String userEmailAddress, MessageService messageService);

    void setUserEmailAddress(String userEmailAddress);

    void setSubject(MessageService messageService);

    void setText(MessageService messageService);
}
