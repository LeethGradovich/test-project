package com.example.bpnotification.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
public class EmailRequest {
    @NotBlank
    @Getter
    @Setter
    private String email;
}
