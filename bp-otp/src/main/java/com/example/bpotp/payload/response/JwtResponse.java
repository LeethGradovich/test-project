package com.example.bpotp.payload.response;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String phoneNumber;

    public JwtResponse(String accessToken, Long id, String phoneNumber){
        this.token=accessToken;
        this.id=id;
        this.phoneNumber=phoneNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
