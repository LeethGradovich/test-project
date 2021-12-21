package com.example.bpnotification.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MailSenderService {

    private final MailSender sender;
    private final MailMessage message;

    public MailSenderService(MailSender sender) {
        this.sender = sender;
        message = new SimpleMailMessage();
    }

    public void sendMessage(String userEmailAddress, String subject, String text) {
        setMessage(userEmailAddress, subject, text);
        try {
            sender.send((SimpleMailMessage) message);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void setMessage(String userEmailAddress, String subject, String text) {
        setUserEmailAddress(userEmailAddress);
        setSubject(subject);
        setText(text);
    }

    public void setUserEmailAddress(String userEmailAddress) {
        message.setTo(userEmailAddress);
    }

    public void setSubject(String subject) {
        message.setSubject(subject);
    }

    public void setText(String text) {
        message.setText(text);
    }
}
