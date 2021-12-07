package com.example.bpotp.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
public class PhoneRequest {
    @NotBlank
    @Getter
    @Setter
    private String phoneNumber;
}
