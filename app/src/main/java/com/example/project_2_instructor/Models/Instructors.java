package com.example.project_2_instructor.Models;

public class Instructors {

    private int ins_id;
    private String name_class;
    private String firstName;
    private String lastName;
    private String Email;
    private String Password;
    private boolean Type;
    private String token;


    public Instructors() {
    }

    public Instructors(int ins_id, String name_class, String firstName, String lastName, String email, String password, boolean type) {
        this.ins_id = ins_id;
        this.name_class = name_class;
        this.firstName = firstName;
        this.lastName = lastName;
        Email = email;
        Password = password;
        Type = type;
    }

    public int getIns_id() {
        return ins_id;
    }

    public String getName_class() {
        return name_class;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public boolean isType() {
        return Type;
    }

    public String getToken() {
        return token;
    }
}
