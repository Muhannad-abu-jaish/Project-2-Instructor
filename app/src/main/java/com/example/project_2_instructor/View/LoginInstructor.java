package com.example.project_2_instructor.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_2_instructor.Constant.CONSTANT;
import com.example.project_2_instructor.Models.API;
import com.example.project_2_instructor.Models.DataLogin;
import com.example.project_2_instructor.Models.Instructors;
import com.example.project_2_instructor.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.messaging.FirebaseMessaging;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInstructor extends AppCompatActivity {


    Button login_btn;
    TextInputEditText email_et,password_et;
    TextView forgotPassword_tv;
    SharedPreferences sharedPreferences;
    String tokenMessage;

    public static final String TOKEN = "token" ;
    public static final String NUM_NOTIFICATION = "num_notification";
    public static final String INSTRUCTOR_DB = "InstructorData";
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
        sharedPreferences = getSharedPreferences(INSTRUCTOR_DB,MODE_PRIVATE);
        if(sharedPreferences.getString(NUM_NOTIFICATION,"").isEmpty()){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(NUM_NOTIFICATION,"0");
            editor.apply();
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("notification_channel", "notification_channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(!task.isSuccessful()){
                    System.err.println("Error Task Message : " + task.getException());
                }else{
                    tokenMessage = task.getResult();
                }
            }
        });
    }//End of initialize



    public void clickButtons()
    {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(checkInputs())
                insertLogin();
               else
                   Toast.makeText(getApplicationContext(),"Error in Inputs!!",Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean checkInputs() {
    String email = email_et.getText().toString();
    String password = password_et.getText().toString();
     if(email.isEmpty()||password.isEmpty())
    {
        return false;
    }
     else if (email_et.getText().toString().length()<3||email_et.getText().toString().length()>10||password_et.getText()
             .toString().length()<3||password_et.getText().toString().length()>15)
     {
         return false;
     }
    else if (!email.matches("[aA-zZ0-9]+")||!password.matches("[aA-zZ0-9]+"))
    {
        return false;
    }
    return true;
    }
    public void insertLogin()
    {

        API api = CONSTANT.CREATING_CALL();
        Call<Instructors> dataLoginCall = api.loginInstructor(new DataLogin(email_et.getText().toString(),password_et.getText().toString(),tokenMessage));
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
        editor.putInt("ins_id",instructors.getId());
        editor.putInt("name_class",instructors.getName_class());
        editor.putString("firstName",instructors.getFirst_name());
        editor.putString("lastName",instructors.getLast_name());
        editor.putString("username",instructors.getUsername());
        editor.putString("Password",instructors.getPassword());
        editor.putString("token",instructors.getToken());
        editor.putString("tokenMessage",instructors.getTokenMessage());
        editor.apply();


    }
}