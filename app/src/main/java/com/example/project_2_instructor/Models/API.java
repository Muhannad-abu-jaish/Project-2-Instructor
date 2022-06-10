package com.example.project_2_instructor.Models;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {

    /*@POST("/login")
   // public Call<Student> loginStudent(@Body DataLogin dataLogin);*/
    @POST("/instructor/login")
    public Call<Instructors> loginInstructor(@Body DataLogin dataLogin);
}
