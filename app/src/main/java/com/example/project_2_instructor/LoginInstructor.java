package com.example.project_2_instructor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.project_2_instructor.Constant.CONSTANT;
import com.example.project_2_instructor.Models.API;
import com.example.project_2_instructor.Models.DataLogin;
import com.example.project_2_instructor.Models.Instructors;
import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInstructor extends AppCompatActivity {


    Button login_btn;
    TextInputEditText email_et,password_et;
    TextView forgotPassword_tv;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_instructor);

        initialize();
        clickButtons();

    }

    public void initialize()
    {
        login_btn=findViewById(R.id.Login_btn);
        email_et=findViewById(R.id.login_ll_1_et_email);
        password_et=findViewById(R.id.login_ll_1_et_2_password);
        forgotPassword_tv=findViewById(R.id.login_ll_1_tv_forgot_password);
        sharedPreferences = getSharedPreferences("InstructorData",MODE_PRIVATE);
    }//End of initialize



    public void clickButtons()
    {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertLogin();

            }
        });
    }

    public void insertLogin()
    {

        API api = CONSTANT.CREATING_CALL();
        Call<Instructors> dataLoginCall = api.loginInstructor(new DataLogin(email_et.getText().toString(),password_et.getText().toString()));
        dataLoginCall.enqueue(new Callback<Instructors>() {
            @Override
            public void onResponse(Call<Instructors> call, Response<Instructors> response) {

                if(response.isSuccessful())
                {
                    saveIntoSharedPreferences(response.body());
                    MainInstructor.redirectActivity(LoginInstructor.this, MainInstructor.class);
                }
                else {
                    try {
                        System.out.println("error successfully"+response.errorBody().string()+ response.code());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Instructors> call, Throwable t) {

                System.out.println("error "+ t.getMessage());


            }
        });
    }

    private void saveIntoSharedPreferences(Instructors instructors)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("ins_id",instructors.getIns_id());
        editor.putString("name_class",instructors.getName_class());
        editor.putString("firstName",instructors.getFirstName());
        editor.putString("lastName",instructors.getLastName());
        editor.putString("Email",instructors.getEmail());
        editor.putString("Password",instructors.getPassword());
        editor.putString("token",instructors.getToken());
        System.out.println("first name: "+instructors.getFirstName()+"      last name : "+ instructors.getLastName()+"    name class : "+ instructors.getName_class());
        System.out.println("email: "+instructors.getEmail()+"      password : "+ instructors.getPassword());

        editor.apply();


    }
}