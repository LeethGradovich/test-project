package com.example.bpotp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class JwtDTO {
    @Getter
    private final String token;
    @Getter
    private final String type = "Bearer";
    @Getter
    private final String phoneNumber;
}
