package com.example.bpotp.payload.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtResponse {
    @Getter
    private final String token;
    @Getter
    private final String type = "Bearer";
    @Getter
    private final String phoneNumber;
}
