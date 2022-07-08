package com.example.project_2_instructor.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project_2_instructor.R;

public class Choice_students extends AppCompatActivity {
  Button attendance, students ;
  Bundle bundle ;
  DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_students);
        init();
        ShowStudents();
        ShowAttendance();
    }

    private void ShowAttendance() {
        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Choice_students.this,Attendance_Students.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        }

    private void ShowStudents() {

       students.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(Choice_students.this,Students_Page.class);
               intent.putExtras(bundle);
               startActivity(intent);
           }
       });

    }

    private void init(){
        students = findViewById(R.id.show_students);
        attendance = findViewById(R.id.attendance);
        bundle = getIntent().getExtras();
        drawerLayout = findViewById(R.id.choice_drawer_layout);
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

    public void ClickNotification(View view){
        MainInstructor.redirectActivity(this,Show_Notification.class);
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
        MainInstructor.redirectActivity(this,MainInstructor.class);

    }//End of ClickHome


    public void ClickPersonalProfile(View view)//PersonalProfile
    {
        //Redirect activity to personal profile
        finish();
        MainInstructor.redirectActivity(this,Personal_profile.class);

    }//End of ClickPersonalProfile



    // يفترض أضيف ال settings


    public void ClickAboutUs(View view)
    {

        //Redirect activity to about us
        //finish();
        // redirectActivity(this , AboutUs.class );

    }//End of ClickِAboutUs

    public void ClickLogOut(View view)
    {
        System.out.println(" am in about from Main parent");
        //Close app
        MainInstructor.logout(this);

    }//End of ClickِAboutUs

}