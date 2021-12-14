package com.example.bpnotification.message;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Component
@NoArgsConstructor
public class RequestNotFinishedMessage {
  private final ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
  @Getter
  private final String subject = resourceBundle.getString("message.subject");
  @Getter
  private final String text = resourceBundle.getString("message.text");
}
