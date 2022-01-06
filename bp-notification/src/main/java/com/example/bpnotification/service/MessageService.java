package com.example.bpnotification.service;

import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    String getMessageSubject();

    String getMessageText();
}
