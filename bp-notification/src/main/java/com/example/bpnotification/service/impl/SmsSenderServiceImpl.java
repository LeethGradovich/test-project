package com.example.bpnotification.service.impl;

import com.example.bpnotification.message.RequestNotFinishedMessage;
import com.example.bpnotification.service.SmsSenderService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;

@RequiredArgsConstructor
@Service
public class SmsSenderServiceImpl implements SmsSenderService {
    @Value("${twilio.account.sid}")
    private String ACCOUNT_SID;
    @Value("${twilio.account.token}")
    private String AUTH_TOKEN;
    @Value("${twilio.account.phone}")
    private String SERVER_PHONE_NUMBER;
    private final String STATUS_URL = "https://eniea1pb31oua.x.pipedream.net";
    private final RequestNotFinishedMessage sms;

    public void sendSMS(String userPhone) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        val message = Message.creator(
                        new com.twilio.type.PhoneNumber(userPhone),
                        new com.twilio.type.PhoneNumber(SERVER_PHONE_NUMBER),
                        sms.getText())
                .setStatusCallback(URI.create(STATUS_URL))
                .create();
    }
}
