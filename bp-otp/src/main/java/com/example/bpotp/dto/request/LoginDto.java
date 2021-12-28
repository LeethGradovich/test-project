package com.example.bpotp.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class LoginDto {
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private int code;
}
