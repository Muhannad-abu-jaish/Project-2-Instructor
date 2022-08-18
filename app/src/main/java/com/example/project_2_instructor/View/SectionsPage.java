package com.example.project_2_instructor.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_2_instructor.Constant.CONSTANT;
import com.example.project_2_instructor.Controller.Adapter_show_sections;
import com.example.project_2_instructor.Models.API;
import com.example.project_2_instructor.Models.NotesClass;
import com.example.project_2_instructor.Models.Section;
import com.example.project_2_instructor.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SectionsPage extends AppCompatActivity {
RecyclerView recyclerView;
Adapter_show_sections adapter_show_sections;
String mytoken ;
SharedPreferences sharedPreferences;
DrawerLayout drawerLayout;
    EditText title , messages ;
    String y ;
    String m;
    String d ;
    Button send;
    String exp_date , Title , message , type;
    TextView name_tool_bar , num_notification ;
    View noConnection;
    Button Retry;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections_page);
        init();
        getSections();
        RetryConnection();
    }

    @Override
    protected void onStart() {
        if(!sharedPreferences.getString(CONSTANT.NUM_NOTIFICATION,"").equals("0")){
            num_notification.setVisibility(View.VISIBLE);
            num_notification.setText(sharedPreferences.getString(CONSTANT.NUM_NOTIFICATION,""));
        }else{
            num_notification.setVisibility(View.GONE);
        }
        super.onStart();
    }
    private void RetryConnection() {
        Retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noConnection.setVisibility(View.GONE);
                    getSections();
            }
        });
    }
    private void Add_notes() {
            View view2 = getLayoutInflater().inflate(R.layout.alert_add_notes_class,null);
            AlertDialog alertDialog = new AlertDialog.Builder(SectionsPage.this)
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
            ArrayAdapter<Integer> yearsAdapter = new ArrayAdapter(SectionsPage.this,android.R.layout.simple_list_item_1,years);
            ArrayAdapter<Integer> monthsAdapter = new ArrayAdapter(SectionsPage.this,android.R.layout.simple_list_item_1, months);
            ArrayAdapter<Integer> daysAdapter = new ArrayAdapter(SectionsPage.this,android.R.layout.simple_list_item_1,days);
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
                    type = "To Class";
                    exp_date = y + "-" + m + "-" + d;
                    Title = title.getText().toString();
                    message = messages.getText().toString();
//                    int[] sec = new int[sections.size()+1];
//                    for(int i = 0 ;i <sections.size();i++){
//                        sec[i] = sections.get(i);
//                    }
                    NotesClass notesClass = new NotesClass(Title,exp_date,message);
                    API api = CONSTANT.CREATING_CALL();
                    Call<ResponseBody> responseBodyCall = api.sendNoteToClass(mytoken,notesClass);
                    responseBodyCall.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(SectionsPage.this,"The Adverts is sent ",Toast.LENGTH_LONG).show();
                                alertDialog.cancel();
                            }else{
                                try {
                                    progressBar.setVisibility(View.GONE);
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
                            System.out.println("error : " + t.getMessage());
                        }
                    });
                }
            });
}

ArrayList<Integer> sections = new ArrayList<>();
    private void getSections() {
        API api = CONSTANT.CREATING_CALL();
        Call<ArrayList<Section>> arrayListCall = api.seeAllSections(mytoken);
        arrayListCall.enqueue(new Callback<ArrayList<Section>>() {
            @Override
            public void onResponse(Call<ArrayList<Section>> call, Response<ArrayList<Section>> response) {
                if(response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    if(response.body().size()==0) {
                      noConnection.setVisibility(View.VISIBLE);
                    }else{
                        adapter_show_sections.setSections(SectionsPage.this, response.body());
                        //addToSections(response.body());
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
            public void onFailure(Call<ArrayList<Section>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                System.out.println("Error : " + t.getMessage());
                noConnection.setVisibility(View.VISIBLE);
            }
        });


}

   /* private void addToSections(ArrayList<Section> body) {
    for(int i = 0 ;i < body.size();i++){
       sections.add(body.get(i).getId());
    }
    }*/

    private void setAdapter() {
    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
    recyclerView.setAdapter(adapter_show_sections);
    }

    private void init(){
        progressBar = findViewById(R.id.progress_sections);
        name_tool_bar = findViewById(R.id.add_private_note_tool_bar_tv);
        name_tool_bar.setText(R.string.SECTIONS);
        Retry = findViewById(R.id.retry_connection);
        noConnection = findViewById(R.id.view_NoConnection);
        recyclerView = findViewById(R.id.recycler_sections);
        adapter_show_sections = new Adapter_show_sections();
        sharedPreferences = getSharedPreferences(CONSTANT.INSTRUCTOR_DB,MODE_PRIVATE);
        mytoken = sharedPreferences.getString(CONSTANT.TOKEN,"");
        drawerLayout = findViewById(R.id.section_drawer);


        //add to Attendance_Students
        num_notification = findViewById(R.id.tool_bar_add_private_note_menu_num_notification_tv) ;
        if(!sharedPreferences.getString(CONSTANT.NUM_NOTIFICATION,"").equals("0")){
            num_notification.setVisibility(View.VISIBLE);
            num_notification.setText(sharedPreferences.getString(CONSTANT.NUM_NOTIFICATION,""));
        }
}
    public  void Click_Add_notes(View view){
        Add_notes();
}

    public void ClickNotification(View view)
    {
        CONSTANT.redirectActivity(this,ParentsNote.class);
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
        recreate();
    }//End of ClickHome


    public void ClickPersonalProfile(View view)//PersonalProfile
    {
        //Redirect activity to personal profile
        finish();
        CONSTANT.redirectActivity(this,Personal_profile.class);

    }//End of ClickPersonalProfile



    // يفترض أضيف ال settings




    public void ClickLogOut(View view)
    {
        //Close app
        CONSTANT.logout(this);
        finish();
        CONSTANT.redirectActivity(this,LoginInstructor.class);
    }//End of ClickِAboutUs

    @Override
    protected void onPause() {
        super.onPause();

        //Close drawer
        closeDrawer(drawerLayout);
    }
}