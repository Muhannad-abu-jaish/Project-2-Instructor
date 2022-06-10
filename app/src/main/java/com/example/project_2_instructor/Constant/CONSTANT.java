package com.example.project_2_instructor.Constant;

import com.example.project_2_instructor.Models.API;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CONSTANT {

    public static String URL = "http://192.168.1.107:3000/";
    public static String URL_EMULATOR = "http://10.0.2.2:3000/";
    public static API CREATING_CALL(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_EMULATOR)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        API api = retrofit.create(API.class);
        System.out.println("now am the editor");
        return api;
    }
}
