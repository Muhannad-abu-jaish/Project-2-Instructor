package com.example.project_2_instructor.Models;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface API {

    /*@POST("/login")
   // public Call<Student> loginStudent(@Body DataLogin dataLogin);*/
    @POST("/instructor/login")
    public Call<Instructors> loginInstructor(@Body DataLogin dataLogin);
    @GET("/instructor/see_sections")
    public Call<ArrayList<Section>> seeAllSections(@Header("Authorization") String token);
    @GET("/instructor/see_students/{sectionID}")
    public Call<ArrayList<Student>> seeAllStudents(@Header("Authorization") String token , @Path("sectionID") int sec_id);
    @GET("/instructor/getComplaint")
    public Call<ArrayList<ParentsNotes>> getComplaints(@Header("Authorization") String token) ;
    @POST("/instructor/check_attendance")
    public Call<ResponseBody> sendAttendance(@Header("Authorization") String token,@Body Absence students_array) ;
    @POST("/instructor/add-note/{studentID}")
    public Call<ResponseBody> sendNoteToStudent(@Header("Authorization") String token,@Path("studentID") int student_id , @Body Note note);
    @POST("/instructor/add_class_note")
    public Call<ResponseBody> sendNoteToClass(@Header("Authorization") String token , @Body NotesClass notesClass);
    @POST("/instructor/add_section_note/{sectionID}")
    public Call<ResponseBody> sendNoteToSection(@Header("Authorization") String token ,@Path("sectionID") int sec_id , @Body NoteSection noteSection);
    @POST("/instructor/addAbsenceNote/{studentID}")
    public Call<ResponseBody> sendAbsenceWarning(@Header("Authorization") String token ,@Path("studentID") int student_id, @Body Absence_Data absence_data);


}
