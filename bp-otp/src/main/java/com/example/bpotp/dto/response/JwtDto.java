package com.example.bpotp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JwtDto {
    private final String token;
    private final String type = "Bearer";
    private final String phoneNumber;
}
