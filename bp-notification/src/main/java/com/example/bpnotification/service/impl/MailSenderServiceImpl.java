package com.example.bpnotification.service.impl;

import com.example.bpnotification.service.MailSenderService;
import com.example.bpnotification.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MailSenderServiceImpl implements MailSenderService {
    private final MailSender sender;
    private final MailMessage message;

    public MailSenderServiceImpl(MailSender sender) {
        this.sender = sender;
        message = new SimpleMailMessage();
    }

    public void sendMessage(String userEmailAddress, MessageService messageService) {
        setMessage(userEmailAddress, messageService);
        try {
            sender.send((SimpleMailMessage) message);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void setMessage(String userEmailAddress, MessageService messageService) {
        setUserEmailAddress(userEmailAddress);
        setSubject(messageService);
        setText(messageService);
    }

    public void setUserEmailAddress(String userEmailAddress) {
        message.setTo(userEmailAddress);
    }

    public void setSubject(MessageService messageService) {
        message.setSubject(messageService.getMessageSubject());
    }

    public void setText(MessageService messageService) {
        message.setText(messageService.getMessageText());
    }
}
