package com.example.bpotp.payload.request;

import javax.validation.constraints.NotBlank;

public class PhoneRequest {
    @NotBlank
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
