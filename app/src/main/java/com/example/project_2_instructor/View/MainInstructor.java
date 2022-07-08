package com.example.project_2_instructor.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.project_2_instructor.R;
import com.example.project_2_instructor.View.LoginInstructor;

public class MainInstructor extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Button LogToSchool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_instructor);
        initialize();
        ClickLogging();

    }

    private void ClickLogging() {
        LogToSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(MainInstructor.this,SectionsPage.class);
            }
        });

    }

    public void initialize()
    {
        LogToSchool = findViewById(R.id.LogToSchool);
        drawerLayout = findViewById(R.id.main_instructor_drawer_layout);
    }//End of initialize

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
        recreate();

    }//End of ClickHome
    public void ClickNotification(View view){
        redirectActivity(this,Show_Notification.class);
    }


    public void ClickPersonalProfile(View view)//PersonalProfile
    {
        //Redirect activity to personal profile
        redirectActivity(this  , Personal_profile.class );

    }//End of ClickPersonalProfile



    // يفترض أضيف ال settings


    public void ClickAboutUs(View view)
    {

        //Redirect activity to about us

       // redirectActivity(this , AboutUs.class );

    }//End of ClickِAboutUs

    public void ClickLogOut(View view)
    {
        System.out.println(" am in about from Main parent");
        //Close app
        logout(this);

    }//End of ClickِAboutUs


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

    public static void redirectActivity(Activity activity , Class aClass)
    {
        //Initialize intent
        Intent intent = new Intent(activity , aClass);
        //set flag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Start activity
        activity.startActivity(intent);

    }//End of redirectActivity

    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        closeDrawer(drawerLayout);
    }
}