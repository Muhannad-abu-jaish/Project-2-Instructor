package com.example.project_2_instructor.Constant;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.project_2_instructor.Models.API;
import com.example.project_2_instructor.View.LoginInstructor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CONSTANT {

    public static String Token_student = "Token_student";
    public static String Id_student = "Id_student";
    public static String SECID = "SectionID" ;
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
    public static final String KNOWLEDGE = "knowledge" ;


    public static String firstname_instructor = "firstName";
    public static String lastname_instructor = "lastName";
    public static String NAME_CLASS = "name_class";
    public static String PASSWORD_INSTRUCTOR = "Password";
    public static String USERNAME_INSTRUCTOR = "username";

    public static final String TOKEN = "token" ;
    public static final String NUM_NOTIFICATION = "num_notification";
    public static final String INSTRUCTOR_DB = "InstructorData";


    public static String URL = "http://192.168.1.107:3000/";
    public static String URL_EMULATOR = "http://10.0.2.2:3000/";
    public static API CREATING_CALL(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_EMULATOR)
                .addConverterFactory(GsonConverterFactory.create(gson)).build();
        API api = retrofit.create(API.class);
        return api;
    }

    public static void redirectActivity(final Activity activity , Class aClass)
    {
        //Initialize intent
        Intent intent = new Intent(activity , aClass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Start activity
        activity.startActivity(intent);

    }

    public static  void logout( final Activity activity)
    {
        //Initialize alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //Set title
        builder.setTitle("Logout");
        //Set message
        builder.setMessage("Are you sure yoy want to logout ?");
        //positive yes button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Finish activity
                redirectActivity(activity, LoginInstructor.class);
                activity.finishAffinity();
                //Exit app
                System.exit(0);
            }
        });
        //Negative no button
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dismiss dialog
                dialog.dismiss();
            }
        });
        //Show dialog
        builder.show();
    }//End of logout

    public static void closeDrawer(DrawerLayout drawerLayout)
    {
        //Close drawer layout
        //Check condition
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            //When drawer is open
            //Close drawer
            drawerLayout.closeDrawer(GravityCompat.START);

        }

    }//End of closeDrawer

    public static void openDrawer(DrawerLayout drawerLayout)
    {
        //Open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }//End of open Drawer
}
