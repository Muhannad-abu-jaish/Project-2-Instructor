package com.example.project_2_instructor.View;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project_2_instructor.Constant.CONSTANT;
import com.example.project_2_instructor.Models.API;
import com.example.project_2_instructor.Models.Note;
import com.example.project_2_instructor.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentProfile extends AppCompatActivity {

TextView rankStudent , username_student,name_class,name_section,fullname_student,attendance_number,
        absence_number,age_student,birthdate_student , Back ;
Bundle bundle;
int id_student;
Toolbar toolbar;
Button  add_note_private , add_absence_warning;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);
        init();
        getData();
        sendPrivateNote();
        sendAbsenceWarning();



    }

    private void sendAbsenceWarning() {
    add_absence_warning.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            View send_absence_warning = getLayoutInflater().inflate(R.layout.alert_add_asbence_warning,null);
            AlertDialog alertDialog = new AlertDialog.Builder(StudentProfile.this)
                    .setView(send_absence_warning).show();
            TextInputLayout textInputLayout = send_absence_warning.findViewById(R.id.send_absence_warning);
            TextInputEditText textInputEditText = send_absence_warning.findViewById(R.id.text_field_send_warning);
            textInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(textInputEditText.getText().toString().isEmpty()){
                       Toast toast = Toast.makeText(StudentProfile.this,"The Field is Empty!!",Toast.LENGTH_LONG);
                       toast.getView().setBackground(getResources().getDrawable(R.color.Pink));
                       toast.show();
                    }
                    else{
                        Toast toast = Toast.makeText(StudentProfile.this,"The Message is sent !!",Toast.LENGTH_LONG);
                        toast.getView().setBackground(getResources().getDrawable(R.color.Pink));
                        toast.show();
                        textInputEditText.getText().clear();
                    }
                }
            });
        }
    });
}
    private void sendPrivateNote() {
   add_note_private.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
        View send_note = getLayoutInflater().inflate(R.layout.alert_add_notes_student,null);
           AlertDialog alertDialog = new AlertDialog.Builder(StudentProfile.this)
                   .setView(send_note).show();
           TextInputLayout textInputLayout = send_note.findViewById(R.id.send_private_note);
           TextInputEditText textInputEditText = send_note.findViewById(R.id.text_field_send_note);
           textInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if(textInputEditText.getText().toString().isEmpty()){
                       Toast toast = Toast.makeText(StudentProfile.this,"The Field is Empty!!",Toast.LENGTH_LONG);
                       toast.getView().setBackground(getResources().getDrawable(R.color.Pink));
                       toast.show();
                   }
                   else{
                       API api = CONSTANT.CREATING_CALL();
                       System.out.println("TextInput : "  +textInputEditText.getText().toString());
                       Note note = new Note(textInputEditText.getText().toString());
                       Call<ResponseBody> responseBodyCall = api.sendNoteToStudent(id_student,note);
                       responseBodyCall.enqueue(new Callback<ResponseBody>() {
                           @Override
                           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                               if(response.isSuccessful()){
                                   Toast toast = Toast.makeText(StudentProfile.this,"The Message is sent !!",Toast.LENGTH_LONG);
                                   toast.getView().setBackground(getResources().getDrawable(R.color.Pink));
                                   toast.show();
                                   textInputEditText.getText().clear();
                                   alertDialog.cancel();
                               }else{
                                   try {
                                       System.out.println("Error successfully : " + response.errorBody().string());
                                   } catch (IOException e) {
                                       e.printStackTrace();
                                   }
                               }
                           }

                           @Override
                           public void onFailure(Call<ResponseBody> call, Throwable t) {
                               System.out.println("error is : " + t.getMessage());
                           }
                       });





                   }
               }
           });
       }
   });

}

    private void getData() {
    rankStudent.setText("%"+bundle.getInt(CONSTANT.rank_student));
    username_student.setText(""+bundle.getString(CONSTANT.username_student));
    fullname_student.setText(""+bundle.getString(CONSTANT.firstname_student)+" "+bundle.getString(CONSTANT.lastname_student));
    birthdate_student.setText(""+bundle.getString(CONSTANT.bitrthdate_student));
    name_class.setText("class : "+bundle.getInt(CONSTANT.class_student));
    name_section.setText("section : "+bundle.getInt(CONSTANT.section_student));
    attendance_number.setText(""+bundle.getInt(CONSTANT.attendance_student));
    absence_number.setText(""+bundle.getInt(CONSTANT.absence_student));
    age_student.setText(""+bundle.getInt(CONSTANT.age_student));
    }

    private void init(){
        rankStudent = findViewById(R.id.rank_student_profile);
        username_student = findViewById(R.id.username_student_profile);
        name_class = findViewById(R.id.name_class_student_profile);
        name_section = findViewById(R.id.section_student_profile);
        fullname_student = findViewById(R.id.fullname_student_profile);
        attendance_number = findViewById(R.id.attend_number_student_profile);
        absence_number = findViewById(R.id.absence_number_student_profile);
        age_student = findViewById(R.id.age_student_profile);
        birthdate_student = findViewById(R.id.birth_date_student_profile);
        bundle = getIntent().getExtras();
        id_student = bundle.getInt(CONSTANT.Id_student);
        Back = findViewById(R.id.GoBack_profile);
        add_note_private = findViewById(R.id.add_private_note);
        add_absence_warning = findViewById(R.id.add_absence_warning);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
}
}