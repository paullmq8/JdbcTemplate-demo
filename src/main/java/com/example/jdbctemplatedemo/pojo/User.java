package com.example.jdbctemplatedemo.pojo;

public class User {

    private Long id;
    private String username;
    private String address;

    public User() {}

    public User(String username, String address) {
        this.username = username;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
