package com.example.project_2_instructor.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.project_2_instructor.Constant.CONSTANT;
import com.example.project_2_instructor.R;

import io.grpc.internal.ConscryptLoader;

public class Personal_profile extends AppCompatActivity {
SharedPreferences sharedPreferences;
TextView name_instructor , num_notification;
TextView name_class_instructor;
TextView username_instructor;
TextView password_instructor;
DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);
        init();
        getData();
    }

    private void getData() {
        String NAME = sharedPreferences.getString(CONSTANT.firstname_instructor,"") + sharedPreferences.getString(CONSTANT.lastname_instructor,"");
        name_instructor.setText(NAME);
        name_class_instructor.setText(sharedPreferences.getInt(CONSTANT.NAME_CLASS,0));
        password_instructor.setText(sharedPreferences.getString(CONSTANT.PASSWORD_INSTRUCTOR,""));

        username_instructor.setText(sharedPreferences.getString(CONSTANT.USERNAME_INSTRUCTOR,""));
    }

    private void init(){
        name_class_instructor = findViewById(R.id.name_class_instructor);
        name_instructor = findViewById(R.id.name_Instructor);
        password_instructor = findViewById(R.id.password_instructor);
        username_instructor = findViewById(R.id.username_instructor);
        drawerLayout = findViewById(R.id.personal_profile_drawer_layout);
        num_notification = findViewById(R.id.num_notification);
        sharedPreferences = getSharedPreferences(CONSTANT.INSTRUCTOR_DB, MODE_PRIVATE);
        if(!sharedPreferences.getString(CONSTANT.NUM_NOTIFICATION,"").equals("0")){
            num_notification.setVisibility(View.VISIBLE);
            num_notification.setText(""+sharedPreferences.getString(CONSTANT.NUM_NOTIFICATION,""));
        }
    }
    public void ClickMenu(View view)
    {
        //Open Drawer
        openDrawer(drawerLayout);
    }//End of ClickMenu

    public static void openDrawer(DrawerLayout drawerLayout)
    {
        //Open drawer layout
        drawerLayout.openDrawer(GravityCompat.START);
    }//End of open Drawer


    public void ClickLogo(View view)
    {
        //Close drawer
        closeDrawer(drawerLayout);
    }//End of ClickLogo



    public void ClickNotification(View view)
    {
        CONSTANT.redirectActivity(this,ParentsNote.class);
    }

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

    public void ClickHome( View view)
    {
        //Recreate activity
        finish();
        CONSTANT.redirectActivity(this,SectionsPage.class);

    }//End of ClickHome


    public void ClickPersonalProfile(View view)//PersonalProfile
    {
        //Redirect activity to personal profile
        finish();
        recreate();

    }//End of ClickPersonalProfile





    public void ClickLogOut(View view)
    {
        System.out.println(" am in about from Main parent");
        //Close app
        CONSTANT.logout(this);

    }//End of ClickِAboutUs



}