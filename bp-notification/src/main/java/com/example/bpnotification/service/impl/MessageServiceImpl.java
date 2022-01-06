package com.example.bpnotification.service.impl;

import com.example.bpnotification.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Slf4j
@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;

    public String getMessageSubject() {
        String subject = "";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM public.message");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                subject = resultSet.getString("subject");
            }
        } catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return subject;
    }

    public String getMessageText() {
        String text = "";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM public.message");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                text = resultSet.getString("text");
            }
        } catch (Exception e) {

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return text;
    }
}
