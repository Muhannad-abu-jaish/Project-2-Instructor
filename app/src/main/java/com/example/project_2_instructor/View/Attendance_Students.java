package com.example.project_2_instructor.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_2_instructor.Constant.CONSTANT;
import com.example.project_2_instructor.Controller.AdapterAbsenceFromDB;
import com.example.project_2_instructor.Models.API;
import com.example.project_2_instructor.Models.Absence;
import com.example.project_2_instructor.Models.ListAbsence;
import com.example.project_2_instructor.Models.Student;
import com.example.project_2_instructor.R;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Attendance_Students extends AppCompatActivity {

    ArrayList<Student> students ;
    Button sendDB ;
    int secId ;
    RecyclerView recyclerView ;
    AdapterAbsenceFromDB adapterAbsenceFromDB ;
    SharedPreferences sharedPreferences;
    DrawerLayout drawerLayout;
    TextView name_tool_bar ;
    CheckBox checkBox ;
    int knowledge ;
    String studentName ;
    View noConnection;
    Button Retry;
    Bundle bundle ;
    String mytoken;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance__students);


        init() ;

        getStudents() ;

        sendToFinal();

        RetryConnection();
    }

    private void RetryConnection() {
        Retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noConnection.setVisibility(View.GONE);
                getStudents();
            }
        });
    }

    private void sendToFinal()
    {
        sendDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendAttendanceStatus();

                for (int i=0 ; i<adapterAbsenceFromDB.getAbsencesStudents().size() ; i++)
                {
                    System.out.println("--------"+adapterAbsenceFromDB.getAbsencesStudents().get(i).getId() + "// " +adapterAbsenceFromDB.getAbsencesStudents().get(i).isAbsence()+"---------------");
                }
            }
        });
    }

    private void sendAttendanceStatus()
    {
        API api = CONSTANT.CREATING_CALL();
        Absence absence = new Absence(adapterAbsenceFromDB.getAbsencesStudents());
        Call<ResponseBody> call = api.sendAttendance(mytoken,absence) ;
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful())
                {
                    Toast.makeText(getApplicationContext() , " Send is successful", Toast.LENGTH_SHORT).show();
                }

                else{
                    try {
                        System.out.println("error successfully : " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext() , t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getStudents ()
    {
        API api = CONSTANT.CREATING_CALL();
        Call<ArrayList<Student>> arrayListCall = api.seeAllStudents(mytoken,secId);
        arrayListCall.enqueue(new Callback<ArrayList<Student>>() {
            @Override
            public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
                if(response.isSuccessful()){
                    if (response.body().size()==0) {
                        noConnection.setVisibility(View.VISIBLE);
                    }else
                    {
                        adapterAbsenceFromDB.setStudents(response.body());
                        students = adapterAbsenceFromDB.getStudents();
                        setAdapterAbsenceFromDB();
                    }

                }else{
                    try {
                        Toast.makeText(getApplicationContext(),response.errorBody().string(),Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Student>> call, Throwable t) {
                System.out.println("Error : " + t.getMessage());
                noConnection.setVisibility(View.VISIBLE);
            }
        });
    }


    private void setAdapterAbsenceFromDB() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapterAbsenceFromDB);
    }
    public void init()
    {

        Retry = findViewById(R.id.retry_connection);
        noConnection = findViewById(R.id.view_NoConnection);
        students = new ArrayList<>() ;
        bundle = getIntent().getExtras() ;
        secId = bundle.getInt(CONSTANT.SECID) ;
        sharedPreferences = getSharedPreferences(CONSTANT.INSTRUCTOR_DB , MODE_PRIVATE) ;
        knowledge = sharedPreferences.getInt(CONSTANT.KNOWLEDGE , 0 ) ;
        mytoken = sharedPreferences.getString(CONSTANT.TOKEN,"");
        sendDB = findViewById(R.id.attendance_students_send) ;
        name_tool_bar = findViewById(R.id.add_private_note_tool_bar_tv) ;
        name_tool_bar.setText(R.string.STUDENTS);
        drawerLayout = findViewById(R.id.absence_drawer) ;
        recyclerView = findViewById(R.id.absence_recycler_students) ;
        adapterAbsenceFromDB = new AdapterAbsenceFromDB() ;

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
        CONSTANT.redirectActivity(this,SectionsPage.class);

    }//End of ClickHome


    public void ClickPersonalProfile(View view)//PersonalProfile
    {
        //Redirect activity to personal profile
        finish();
        CONSTANT.redirectActivity(this,Personal_profile.class);

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
        CONSTANT.logout(this);

    }//End of ClickِAboutUs

}