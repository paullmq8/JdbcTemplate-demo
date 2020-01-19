package com.example.jdbctemplatedemo.dto;

import com.example.jdbctemplatedemo.pojo.User;

import javax.validation.constraints.NotNull;

public class UserUpdateDTO {

    @NotNull
    private String username;
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User convertToUser() {
        return new User(this.getUsername(), this.getAddress());
    }
}
