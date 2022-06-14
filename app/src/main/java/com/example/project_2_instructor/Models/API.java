package com.example.project_2_instructor.Models;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface API {

    /*@POST("/login")
   // public Call<Student> loginStudent(@Body DataLogin dataLogin);*/
    @POST("/instructor/login")
    public Call<Instructors> loginInstructor(@Body DataLogin dataLogin);
    @GET("/instructor/see_sections")
    public Call<ArrayList<Section>> seeAllSections(@Header("token") String token);
    @GET("/instructor/see_students")
    public Call<ArrayList<Student>> seeAllStudents(@Header("sectionID") int sec_id);
}
