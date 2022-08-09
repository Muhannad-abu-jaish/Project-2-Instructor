package com.example.project_2_instructor.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents_note);

        init();
        try {
            GET_ANNOUNCEMENT();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void GET_ANNOUNCEMENT() throws InterruptedException {
        API api = CONSTANT.CREATING_CALL();
        Call<ArrayList<ParentsNotes>> call = api.getComplaints(myToken);
        call.enqueue(new Callback<ArrayList<ParentsNotes>>() {
            @Override
            public void onResponse(Call<ArrayList<ParentsNotes>> call, Response<ArrayList<ParentsNotes>> response) {
                if(response.isSuccessful()){
                    adapterParentsNote.setParentsNotes(response.body());
                    setAdapterParentsNote(adapterParentsNote);
                    addParentsNoteToDataBase(response) ;

                }else{
                    try {
                        System.out.println("Error Statues !" + response.code() + "\t Error Body : " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<ParentsNotes>> call, Throwable t) {
                System.out.println("Error : " + t.getMessage());
            }
        });
    }
    public void init(){

        parentsNotesDB = new ParentsNotesDB(this);
        parentsNotes = parentsNotesDB.getAllPrivateNotes();
        recyclerView = findViewById(R.id.parents_note_rv_notes);
        drawerLayout = findViewById(R.id.parents_note_drawer_layout);
        adapterParentsNote = new AdapterParentsNote(this , parentsNotes);
        sharedPreferences = getSharedPreferences(LoginInstructor.INSTRUCTOR_DB,MODE_PRIVATE);
        myToken = sharedPreferences.getString(LoginInstructor.TOKEN,"");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(LoginInstructor.NUM_NOTIFICATION,"0");
        editor.apply();
        setAdapterParentsNote(adapterParentsNote); ;
    }


    public void addParentsNoteToDataBase (Response<ArrayList<ParentsNotes>> response)
    {
        parentsNotes = response.body();
        for (int i = 0 ;i<parentsNotes.size() ; i++)
        {
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
        MainInstructor.openDrawer(drawerLayout);
    }//End of ClickMenu


    public void ClickLogo(View view)
    {
        //Close drawer
        MainInstructor.closeDrawer(drawerLayout);
    }//end of ClickLogo

    public void ClickHome(View view)
    {
        //Redirect activity to home
        MainInstructor.redirectActivity(this , MainInstructor.class);
    }//End of ClickHome


    public void ClickAboutUs(View view)
    {
        //Recreate activity
       // MainInstructor.redirectActivity(this , MainInstructor.class);
    }//End of ClickAboutUs

    public void ClickPersonalProfile(View view)
    {
        //Redirect activity to dashboard
        MainInstructor.redirectActivity(this , Personal_profile.class);
    }//End of ClickDashboard

    public void ClickLogOut(View view)
    {
        //Close app
        MainInstructor.logout(this);
    }//End of ClickLogout


    @Override
    protected void onPause() {
        super.onPause();
        //Close drawer
        MainInstructor.closeDrawer(drawerLayout);
    }
}