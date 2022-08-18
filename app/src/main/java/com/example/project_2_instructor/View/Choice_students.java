package com.example.project_2_instructor.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.project_2_instructor.Constant.CONSTANT;
import com.example.project_2_instructor.R;

public class Choice_students extends AppCompatActivity {

  Bundle bundle ;
  DrawerLayout drawerLayout;
  LinearLayout attendanceStudents ,showStudents ;
  SharedPreferences sharedPreferences;
  TextView num_notification;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_students);
        init();
        ShowStudents();
        ShowAttendance();
    }

    private void ShowAttendance() {
        showStudents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Choice_students.this,Attendance_Students.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        }

  private void ShowStudents() {

      attendanceStudents.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(Choice_students.this,Students_Page.class);
               intent.putExtras(bundle);
               startActivity(intent);
           }
       });

    }

    private void init(){
        attendanceStudents = findViewById(R.id.choice_students_check_students_ll);
        showStudents = findViewById(R.id.choice_students_show_students_ll);
        bundle = getIntent().getExtras();
        drawerLayout = findViewById(R.id.choice_drawer_layout);
        num_notification = findViewById(R.id.num_notification);
        sharedPreferences = getSharedPreferences(CONSTANT.INSTRUCTOR_DB, MODE_PRIVATE);
        if(!sharedPreferences.getString(CONSTANT.NUM_NOTIFICATION,"").equals("0")){
            num_notification.setVisibility(View.VISIBLE);
            num_notification.setText(sharedPreferences.getString(CONSTANT.NUM_NOTIFICATION,""));
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
        CONSTANT.redirectActivity(this,Personal_profile.class);

    }//End of ClickPersonalProfile



    public void ClickLogOut(View view)
    {
        System.out.println(" am in about from Main parent");
        //Close app
        CONSTANT.logout(this);

    }//End of ClickِAboutUs

}