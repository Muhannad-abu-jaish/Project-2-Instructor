package com.example.project_2_instructor.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.resources.Compatibility;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_2_instructor.Constant.CONSTANT;
import com.example.project_2_instructor.Controller.Adapter_show_sections;
import com.example.project_2_instructor.Controller.Adapter_show_students;
import com.example.project_2_instructor.Models.API;
import com.example.project_2_instructor.Models.NoteSection;
import com.example.project_2_instructor.Models.Student;
import com.example.project_2_instructor.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Students_Page extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter_show_students adapter_show_students;
    int Sec_Id ;
    String mytoken ;
    Bundle bundle;
    SharedPreferences sharedPreferences;
    DrawerLayout drawerLayout;
    TextView name_tool_bar , num_notification;
    EditText title , messages ;
    String y ;
    String m;
    String d ;
    Button send;
    String exp_date , Title , message , type;
    View noConnection;
    Button Retry;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students__page);
        init();
        getStudents();
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
    private void getStudents() {
        API api = CONSTANT.CREATING_CALL();
        Call<ArrayList<Student>> arrayListCall = api.seeAllStudents(mytoken,Sec_Id);
        arrayListCall.enqueue(new Callback<ArrayList<Student>>() {
            @Override
            public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
                if(response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    if(response.body().size()==0){
                        noConnection.setVisibility(View.VISIBLE);
                    }else {
                        adapter_show_students.setStudents(response.body());
                        setAdapter();
                    }
                }else{
                    progressBar.setVisibility(View.GONE);
                    try {
                        noConnection.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(),response.errorBody().string(),Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Student>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                System.out.println("Error : " + t.getMessage());
                noConnection.setVisibility(View.VISIBLE);
            }
        });
    }
    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapter_show_students);
    }

    public  void Click_Add_notes(View view){
        Add_notes();
    }

    private void Add_notes() {
            View view2 = getLayoutInflater().inflate(R.layout.alert_add_notes_class,null);
            AlertDialog alertDialog = new AlertDialog.Builder(Students_Page.this)
                    .setView(view2)
                    .show();
            Spinner year,month,day;
            year = view2.findViewById(R.id.year_notes);
            month = view2.findViewById(R.id.month_note);
            day = view2.findViewById(R.id.day_note);
            title = view2.findViewById(R.id.Title_note_section);
            messages = view2.findViewById(R.id.Text_note_section);
            send = view2.findViewById(R.id.send_note_section);
            List<Integer> years = new ArrayList<>();
            final List<Integer> months = new ArrayList<>();
            List<Integer>days = new ArrayList<>();
            for(int i = 2022;i<2051;i++){
                years.add(i);
            }
            for(int i = 1 ; i<13 ; i++){
                months.add(i);
            }
            for(int i = 1 ; i<31;i++){
                days.add(i);
            }
            ArrayAdapter<Integer> yearsAdapter = new ArrayAdapter(Students_Page.this,android.R.layout.simple_list_item_1,years);
            ArrayAdapter<Integer> monthsAdapter = new ArrayAdapter(Students_Page.this,android.R.layout.simple_list_item_1, months);
            ArrayAdapter<Integer> daysAdapter = new ArrayAdapter(Students_Page.this,android.R.layout.simple_list_item_1,days);
            yearsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            year.setAdapter(yearsAdapter);
            monthsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            month.setAdapter(monthsAdapter);
            daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            day.setAdapter(daysAdapter);
            year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    y = adapterView.getItemAtPosition(i).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    y = adapterView.getItemAtPosition(0).toString();
                }
            });
            month.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    m = adapterView.getItemAtPosition(i).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    m = adapterView.getItemAtPosition(0).toString();
                }
            });
            day.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    d = adapterView.getItemAtPosition(i).toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    d = adapterView.getItemAtPosition(0).toString();
                }
            });
            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    type = "To Section";
                    exp_date = y + "-" + m + "-" + d;
                    Title = title.getText().toString();
                    message = messages.getText().toString();
                    NoteSection noteSection = new NoteSection(Title,exp_date,message);
                    API api = CONSTANT.CREATING_CALL();
                    Call<ResponseBody> responseBodyCall = api.sendNoteToSection(mytoken,Sec_Id,noteSection);
                    responseBodyCall.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(Students_Page.this,"Success Sending ",Toast.LENGTH_LONG).show();
                                alertDialog.cancel();
                            }else{
                                try {
                                    Toast.makeText(getApplicationContext(),response.errorBody().string(),Toast.LENGTH_LONG).show();
                                    System.out.println("Success Body : " + response.errorBody().string());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error connection ,,Please check your connect", Toast.LENGTH_SHORT).show();
                            System.out.println("Error : " + t.getMessage());
                        }
                    });
                }
            });
    }
    private void init(){
        progressBar = findViewById(R.id.progress_students);
        name_tool_bar = findViewById(R.id.add_private_note_tool_bar_tv) ;
        name_tool_bar.setText(R.string.STUDENTS);
        Retry = findViewById(R.id.retry_connection);
        noConnection = findViewById(R.id.view_NoConnection);
        sharedPreferences = getSharedPreferences(CONSTANT.INSTRUCTOR_DB,MODE_PRIVATE);
        mytoken = sharedPreferences.getString(CONSTANT.TOKEN,"");
        recyclerView = findViewById(R.id.recycler_students);
        adapter_show_students = new Adapter_show_students();
        bundle = getIntent().getExtras();
        Sec_Id = bundle.getInt(CONSTANT.SECID);
        drawerLayout = findViewById(R.id.student_drawer);
        num_notification = findViewById(R.id.tool_bar_add_private_note_menu_num_notification_tv) ;
        if(!sharedPreferences.getString(CONSTANT.NUM_NOTIFICATION,"").equals("0")){
            num_notification.setVisibility(View.VISIBLE);
            num_notification.setText(sharedPreferences.getString(CONSTANT.NUM_NOTIFICATION,""));
        }else{
            num_notification.setVisibility(View.GONE);
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
        CONSTANT.redirectActivity(Students_Page.this,SectionsPage.class);
    }//End of ClickHome
    public void ClickPersonalProfile(View view)//PersonalProfile
    {
        //Redirect activity to personal profile
        finish();
        CONSTANT.redirectActivity(this,Personal_profile.class);

    }//End of ClickPersonalProfile


    public void ClickLogOut(View view)
    {
        //Close app
        CONSTANT.logout(this);
        finish();
        CONSTANT.redirectActivity(this,LoginInstructor.class);
    }//End of ClickِAboutUs

}