package com.example.project_2_instructor.Models;

public class DataLogin {

    private String username;
    private String password;

    public DataLogin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
