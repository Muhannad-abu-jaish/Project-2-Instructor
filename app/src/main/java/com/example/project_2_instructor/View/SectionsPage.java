package com.example.project_2_instructor.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.project_2_instructor.Constant.CONSTANT;
import com.example.project_2_instructor.Controller.Adapter_show_sections;
import com.example.project_2_instructor.Models.API;
import com.example.project_2_instructor.Models.Section;
import com.example.project_2_instructor.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SectionsPage extends AppCompatActivity {
RecyclerView recyclerView;
Adapter_show_sections adapter_show_sections;
String mytoken ;
SharedPreferences sharedPreferences;
DrawerLayout drawerLayout;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sections_page);
        init();
        getSections();

    }

    private void getSections() {
        API api = CONSTANT.CREATING_CALL();
        Call<ArrayList<Section>> arrayListCall = api.seeAllSections(mytoken);
        arrayListCall.enqueue(new Callback<ArrayList<Section>>() {
            @Override
            public void onResponse(Call<ArrayList<Section>> call, Response<ArrayList<Section>> response) {
                if(response.isSuccessful()) {
                    adapter_show_sections.setSections(response.body());
                    setAdapter();
                }else{
                    System.out.println("Error successfully!! "+response.code()+"\t" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Section>> call, Throwable t) {
                System.out.println("Error : " + t.getMessage());
            }
        });


}

    private void setAdapter() {
    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false));
    recyclerView.setAdapter(adapter_show_sections);
    }

    private void init(){
        recyclerView = findViewById(R.id.recycler_sections);
        adapter_show_sections = new Adapter_show_sections();
        sharedPreferences = getSharedPreferences("InstructorData",MODE_PRIVATE);
        mytoken = sharedPreferences.getString("token","");
        drawerLayout = findViewById(R.id.section_drawer);
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