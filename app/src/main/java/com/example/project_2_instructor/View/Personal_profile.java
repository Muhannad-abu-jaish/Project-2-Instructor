package com.example.project_2_instructor.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.project_2_instructor.R;

public class Personal_profile extends AppCompatActivity {
SharedPreferences sharedPreferences;
TextView name_instructor;
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

    @SuppressLint("SetTextI18n")
    private void getData() {
        name_instructor.setText(sharedPreferences.getString("firstName","")+sharedPreferences.getString("lastName",""));
        name_class_instructor.setText(" "+sharedPreferences.getInt("name_class",0));
        password_instructor.setText(sharedPreferences.getString("Password",""));
        username_instructor.setText(sharedPreferences.getString("username",""));
    }

    private void init(){
        sharedPreferences = getSharedPreferences("InstructorData",MODE_PRIVATE);
        name_class_instructor = findViewById(R.id.name_class_instructor);
        name_instructor = findViewById(R.id.name_Instructor);
        password_instructor = findViewById(R.id.password_instructor);
        username_instructor = findViewById(R.id.username_instructor);
        drawerLayout = findViewById(R.id.personal_profile_drawer_layout);
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
        recreate();

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
    public void ClickNotification(View view){
        MainInstructor.redirectActivity(this,Show_Notification.class);
    }


}