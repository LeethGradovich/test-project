package com.example.bpnotification.service;

import org.springframework.stereotype.Service;

@Service
public interface MailSenderService {
    void sendMessage(String userEmailAddress, String subject, String text);

    void setMessage(String userEmailAddress, String subject, String text);

    void setUserEmailAddress(String userEmailAddress);

    void setSubject(String subject);

    void setText(String text);
}
