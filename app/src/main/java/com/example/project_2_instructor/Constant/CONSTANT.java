package com.example.project_2_instructor.Constant;

import com.example.project_2_instructor.Models.API;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CONSTANT {

    public static String Token_student = "Token_student";
    public static String Id_student = "Id_student";
    public static String username_student = "username_student";
    public static String firstname_student = "firstname_student";
    public static String lastname_student = "lastname_student";
    public static String age_student = "age_student";
    public static String bitrthdate_student = "bitrthdate_student";
    public static String attendance_student = "attendance_student";
    public static String absence_student = "absence_student";
    public static String class_student = "class_student";
    public static String section_student = "section_student";
    public static String rank_student = "rank_student";









    public static String URL = "http://192.168.1.107:3000/";
    public static String URL_EMULATOR = "http://10.0.2.2:3000/";
    public static API CREATING_CALL(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_EMULATOR)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        API api = retrofit.create(API.class);
        return api;
    }
}
