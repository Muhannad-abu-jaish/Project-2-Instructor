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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.project_2_instructor.Constant.CONSTANT;
import com.example.project_2_instructor.Controller.Adapter_show_sections;
import com.example.project_2_instructor.Controller.Adapter_show_students;
import com.example.project_2_instructor.Models.API;
import com.example.project_2_instructor.Models.NoteSection;
import com.example.project_2_instructor.Models.Student;
import com.example.project_2_instructor.R;

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
    Bundle bundle;
    DrawerLayout drawerLayout;
    EditText title , messages ;
    String y ;
    String m;
    String d ;
    Button send;
    String exp_date , Title , message , type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students__page);
        init();
        getStudents();
    }
    private void getStudents() {
        API api = CONSTANT.CREATING_CALL();
        Call<ArrayList<Student>> arrayListCall = api.seeAllStudents(Sec_Id);
        arrayListCall.enqueue(new Callback<ArrayList<Student>>() {
            @Override
            public void onResponse(Call<ArrayList<Student>> call, Response<ArrayList<Student>> response) {
                if(response.isSuccessful()){
                    adapter_show_students.setStudents(response.body());
                    setAdapter();
                }else{
                    System.out.println("error successfully : " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Student>> call, Throwable t) {
                System.out.println("Error : " + t.getMessage());
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
                    Call<ResponseBody> responseBodyCall = api.sendNoteToSection(Sec_Id,noteSection);
                    responseBodyCall.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(Students_Page.this,"Success Sending ",Toast.LENGTH_LONG).show();
                                alertDialog.cancel();
                            }else{
                                System.out.println("Success Body : " + response.errorBody().toString());
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            System.out.println("Error : " + t.getMessage());
                        }
                    });
                }
            });
    }
    private void init(){
        recyclerView = findViewById(R.id.recycler_students);
        adapter_show_students = new Adapter_show_students();
        bundle = getIntent().getExtras();
        Sec_Id = bundle.getInt("SectionID");
        drawerLayout = findViewById(R.id.student_drawer);
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