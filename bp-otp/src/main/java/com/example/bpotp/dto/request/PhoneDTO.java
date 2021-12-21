package com.example.bpotp.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
public class PhoneDTO {
    @NotBlank
    @Getter
    @Setter
    private String phoneNumber;
}
