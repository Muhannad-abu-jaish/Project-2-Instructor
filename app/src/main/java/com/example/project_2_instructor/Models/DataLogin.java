package com.example.project_2_instructor.Models;

public class DataLogin {

    private String username;
    private String password;
    private String tokenMessage;

    public DataLogin(String username, String password, String tokenMessage) {
        this.username = username;
        this.password = password;
        this.tokenMessage = tokenMessage;
    }

    public String getTokenMessage() {
        return tokenMessage;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
