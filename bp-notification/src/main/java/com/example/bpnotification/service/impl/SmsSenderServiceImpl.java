package com.example.bpnotification.service.impl;

import com.example.bpnotification.service.MessageService;
import com.example.bpnotification.service.SmsSenderService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class SmsSenderServiceImpl implements SmsSenderService {
    @Value("${twilio.account.sid}")
    private String accountSid;
    @Value("${twilio.account.token}")
    private String authToken;
    @Value("${twilio.account.phone}")
    private String serverPhoneNumber;

    @Getter
    private final String statusUrl = "https://eniea1pb31oua.x.pipedream.net";

    public void sendSMS(String userPhone, MessageService messageService) {
        Twilio.init(accountSid, authToken);
        Message.creator(new com.twilio.type.PhoneNumber(userPhone),
                        new com.twilio.type.PhoneNumber(serverPhoneNumber),
                        getText(messageService))
                .setStatusCallback(URI.create(statusUrl))
                .create();
    }

    public String getText(MessageService messageService) {
        return messageService.getMessageText();
    }
}
