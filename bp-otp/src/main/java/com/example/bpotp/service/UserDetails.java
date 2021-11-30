package com.example.bpotp.service;

import com.example.bpotp.model.UserAuth;
import java.util.Objects;

public class UserDetails {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String phoneNumber;

    public UserDetails(Long id, String phoneNumber) {
        this.id = id;
        this.phoneNumber = phoneNumber;
    }

    public static UserDetails build(UserAuth user) {

        return new UserDetails(
                user.getId(),
                user.getPhoneNumber());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetails user = (UserDetails) o;
        return Objects.equals(id, user.id);
    }
}
