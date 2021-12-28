package com.example.bpnotification.service;

import org.springframework.stereotype.Service;

@Service
public interface MailSenderService {
    void sendMessage(String userEmailAddress);

    void setMessage(String userEmailAddress);

    void setUserEmailAddress(String userEmailAddress);

    void setSubject();

    void setText();
}
