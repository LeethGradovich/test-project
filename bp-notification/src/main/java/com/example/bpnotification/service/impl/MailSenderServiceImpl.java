package com.example.bpnotification.service.impl;

import com.example.bpnotification.service.MailSenderService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import java.util.ResourceBundle;

@Slf4j
@Service
public class MailSenderServiceImpl implements MailSenderService {
    private final MailSender sender;
    private final MailMessage message;

    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
    @Getter
    private final String subject = resourceBundle.getString("message.subject");
    @Getter
    private final String text = resourceBundle.getString("message.text");

    public MailSenderServiceImpl(MailSender sender) {
        this.sender = sender;
        message = new SimpleMailMessage();
    }

    public void sendMessage(String userEmailAddress) {
        setMessage(userEmailAddress);
        try {
            sender.send((SimpleMailMessage) message);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void setMessage(String userEmailAddress) {
        setUserEmailAddress(userEmailAddress);
        setSubject();
        setText();
    }

    public void setUserEmailAddress(String userEmailAddress) {
        message.setTo(userEmailAddress);
    }

    public void setSubject() {
        message.setSubject(subject);
    }

    public void setText() {
        message.setText(text);
    }
}
