package com.example.bpotp.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
public class LoginRequest {
    @NotBlank
    @Getter
    @Setter
    private String phoneNumber;
    @NotBlank
    @Getter
    @Setter
    private int code;
}
