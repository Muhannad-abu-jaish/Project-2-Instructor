package com.example.project_2_instructor.Models;

import java.util.Date;

public class Student {
    private int Sid;
    private String first_name;
    private String last_name;
    private String father_name;
    private int age;
    private String username;
    private String password;
    private Date signInDate;
    private Date BirthDate;
    private int attend_number;
    private int absence_number;
    private String token;
    private int name_sec ;
    private int name_class;

    public int getName_sec() {
        return name_sec;
    }

    public int getName_class() {
        return name_class;
    }

    public int getSid() {
        return Sid;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFather_name() {
        return father_name;
    }

    public int getAge() {
        return age;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getSignInDate() {
        return signInDate;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public int getAttend_number() {
        return attend_number;
    }

    public int getAbsence_number() {
        return absence_number;
    }

    public String getToken() {
        return token;
    }

    public Student() {

    }

    public Student(int sid, String first_name, String last_name, String father_name, int age, String username, String password, Date signInDate, Date birthDate, int attend_number, int absence_number, String token,int name_class ,int name_sec) {
        Sid = sid;
        this.first_name = first_name;
        this.last_name = last_name;
        this.father_name = father_name;
        this.age = age;
        this.name_class = name_class;
        this.name_sec = name_sec;
        this.username = username;
        this.password = password;
        this.signInDate = signInDate;
        BirthDate = birthDate;
        this.attend_number = attend_number;
        this.absence_number = absence_number;
        this.token = token;
    }
}
