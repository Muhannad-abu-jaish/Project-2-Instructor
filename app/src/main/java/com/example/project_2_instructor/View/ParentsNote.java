package com.example.project_2_instructor.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_2_instructor.Constant.CONSTANT;
import com.example.project_2_instructor.Controller.AdapterParentsNote;
import com.example.project_2_instructor.Models.API;
import com.example.project_2_instructor.Models.ParentsNotes;
import com.example.project_2_instructor.Models.ParentsNotesDB;
import com.example.project_2_instructor.R;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ParentsNote extends AppCompatActivity {

    ArrayList<ParentsNotes> parentsNotes ;
    RecyclerView recyclerView;
    AdapterParentsNote adapterParentsNote ;
    ParentsNotesDB parentsNotesDB ;
    DrawerLayout drawerLayout;
    String myToken;
    SharedPreferences sharedPreferences ;
    View noConnection;
    TextView name_tool_bar ;
    Button Retry;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_note);

        init();
        try {
            RetryConnection();
            GET_ANNOUNCEMENT();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void RetryConnection() {
        Retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                noConnection.setVisibility(View.GONE);
                try {
                    GET_ANNOUNCEMENT();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void GET_ANNOUNCEMENT() throws InterruptedException {
        API api = CONSTANT.CREATING_CALL();
        Call<ArrayList<ParentsNotes>> call = api.getComplaints(myToken);
        call.enqueue(new Callback<ArrayList<ParentsNotes>>() {
            @Override
            public void onResponse(Call<ArrayList<ParentsNotes>> call, Response<ArrayList<ParentsNotes>> response) {
                if(response.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    if(response.body().size()==0){
                     noConnection.setVisibility(View.VISIBLE);
                     parentsNotesDB.deleteTable();
                    }else {
                        adapterParentsNote = new AdapterParentsNote(getApplicationContext() , response.body());
                        setAdapterParentsNote(adapterParentsNote);
                        addParentsNoteToDataBase(response);
                    }
                }else{
                    progressBar.setVisibility(View.GONE);
                    try {
                        if (parentsNotesDB.getAllParentNotes().size()==0) {
                            Toast.makeText(getApplicationContext(),response.errorBody().string(),Toast.LENGTH_LONG).show();

                        }else
                        {
                            adapterParentsNote = new AdapterParentsNote(getApplicationContext(), parentsNotesDB.getAllParentNotes());
                            setAdapterParentsNote(adapterParentsNote);
                        }
                        Toast.makeText(getApplicationContext(),response.errorBody().string(),Toast.LENGTH_LONG).show();
                        System.out.println("Error Statues !" + response.code() + "\t Error Body : " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<ParentsNotes>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                System.out.println("Error : " + t.getMessage());

                if (parentsNotesDB.getAllParentNotes().size()==0)
                  noConnection.setVisibility(View.VISIBLE);
                else
                {
                    parentsNotes = parentsNotesDB.getAllParentNotes();
                    adapterParentsNote = new AdapterParentsNote(getApplicationContext() , parentsNotes);
                    setAdapterParentsNote(adapterParentsNote);
                }
            }
        });
    }
    public void ClickNotification(View view)
    {
         // nothing
    }

    public void init(){
        progressBar = findViewById(R.id.progress_parent_messages);
        name_tool_bar = findViewById(R.id.main_tool_bar_tv) ;
        name_tool_bar.setText(R.string.THE_PARENTS_MESSAGES);
        Retry = findViewById(R.id.retry_connection);
        noConnection = findViewById(R.id.parent_notes_view_NoConnection);
        parentsNotesDB = new ParentsNotesDB(this);
        parentsNotes =new ArrayList<>() ;



        recyclerView = findViewById(R.id.parents_note_rv_notes);
        drawerLayout = findViewById(R.id.parents_note_drawer_layout);

        sharedPreferences = getSharedPreferences(CONSTANT.INSTRUCTOR_DB,MODE_PRIVATE);
        myToken = sharedPreferences.getString(CONSTANT.TOKEN,"");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CONSTANT.NUM_NOTIFICATION,"0");
        editor.apply();
    }


    public void addParentsNoteToDataBase (Response<ArrayList<ParentsNotes>> response)
    {
        parentsNotes = response.body();
        for (int i = 0 ;i<parentsNotes.size() ; i++)
        {
            boolean status = parentsNotesDB.isExists(parentsNotes.get(i).getId());
            if (!status)
            parentsNotesDB.addParentsNote(parentsNotes.get(i)) ;
        }

    }

    public void setAdapterParentsNote(AdapterParentsNote adapterParentsNote){
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
        recyclerView.setAdapter(adapterParentsNote);
    }

    public void ClickMenu(View view)
    {
        //Open drawer
        CONSTANT.openDrawer(drawerLayout);
    }//End of ClickMenu

    public void ClickLogo(View view)
    {
        //Close drawer
        CONSTANT.closeDrawer(drawerLayout);
    }//end of ClickLogo

    public void ClickHome(View view)
    {
        //Redirect activity to home
        finish();
        CONSTANT.redirectActivity(this , SectionsPage.class);
    }//End of ClickHome


    public void ClickPersonalProfile(View view)
    {
        //Redirect activity to dashboard
        finish();
        CONSTANT.redirectActivity(this , Personal_profile.class);
    }//End of ClickDashboard

    public void ClickLogOut(View view)
    {
        //Close app
        CONSTANT.logout(this);
        finish();
        CONSTANT.redirectActivity(this,LoginInstructor.class);
    }//End of ClickِAboutUs


    @Override
    protected void onPause() {
        CONSTANT.closeDrawer(drawerLayout);
        super.onPause();

    }
}