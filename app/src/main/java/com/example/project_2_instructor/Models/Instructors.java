package com.example.project_2_instructor.Models;

public class Instructors {

    private int id;
    private int name_class;
    private String first_name;
    private String last_name;
    private String username;
    private String password;
    private int role;
    private String token;
    private String tokenMessage;
    public int getId() {
        return id;
    }

    public String getTokenMessage() {
        return tokenMessage;
    }

    public Instructors(int id, int name_class, String first_name, String last_name, String username, String password, int role, String token, String tokenMessage) {
        this.id = id;
        this.name_class = name_class;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.token = token;
        this.tokenMessage = tokenMessage;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    public int getName_class() {
        return name_class;
    }

    public String getToken() {
        return token;
    }

}
