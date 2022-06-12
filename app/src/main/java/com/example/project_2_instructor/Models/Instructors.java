package com.example.project_2_instructor.Models;

public class Instructors {

    private int ins_id;
    private Integer name_class;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean type;
    private String token;

    public Instructors(int ins_id, int name_class, String firstName, String lastName, String username, String password, boolean type, String token) {
        this.ins_id = ins_id;
        this.name_class = name_class;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.type = type;
        this.token = token;
    }

    public int getIns_id() {
        return ins_id;
    }

    public Integer getName_class() {
        return name_class;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isType() {
        return type;
    }

    public String getToken() {
        return token;
    }
}
