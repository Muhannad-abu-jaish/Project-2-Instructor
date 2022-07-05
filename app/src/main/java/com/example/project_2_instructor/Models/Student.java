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
    private String BirthDate;
    private int attend_number;
    private int absence_number;
    private String token;
    private int name_sec ;
    private int name_class;
    private int rank;

    public int getSid() {
        return Sid;
    }

    public void setSid(int sid) {
        Sid = sid;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getFather_name() {
        return father_name;
    }

    public void setFather_name(String father_name) {
        this.father_name = father_name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getSignInDate() {
        return signInDate;
    }

    public void setSignInDate(Date signInDate) {
        this.signInDate = signInDate;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public int getAttend_number() {
        return attend_number;
    }

    public void setAttend_number(int attend_number) {
        this.attend_number = attend_number;
    }

    public int getAbsence_number() {
        return absence_number;
    }

    public void setAbsence_number(int absence_number) {
        this.absence_number = absence_number;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getName_sec() {
        return name_sec;
    }

    public void setName_sec(int name_sec) {
        this.name_sec = name_sec;
    }

    public int getName_class() {
        return name_class;
    }

    public void setName_class(int name_class) {
        this.name_class = name_class;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Student(int sid, String first_name, String last_name, String father_name, int age, String username, String password, Date signInDate, String birthDate, int attend_number, int absence_number, String token, int name_sec, int name_class, int rank) {
        Sid = sid;
        this.first_name = first_name;
        this.last_name = last_name;
        this.father_name = father_name;
        this.age = age;
        this.username = username;
        this.password = password;
        this.signInDate = signInDate;
        BirthDate = birthDate;
        this.attend_number = attend_number;
        this.absence_number = absence_number;
        this.token = token;
        this.name_sec = name_sec;
        this.name_class = name_class;
        this.rank = rank;
    }
}
