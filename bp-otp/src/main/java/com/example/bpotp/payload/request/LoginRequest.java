package com.example.bpotp.payload.request;
import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private int code;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
