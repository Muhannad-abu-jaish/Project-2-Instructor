package com.example.project_2_instructor.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_instructor.Constant.CONSTANT;
import com.example.project_2_instructor.Models.Student;
import com.example.project_2_instructor.R;
import com.example.project_2_instructor.View.StudentProfile;

import java.util.ArrayList;

public class Adapter_show_students extends RecyclerView.Adapter<Adapter_show_students.ViewHolder> {
  ArrayList<Student>students = new ArrayList<>();
  Bundle bundle = new Bundle();
    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }
    @NonNull
    @Override
    public Adapter_show_students.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.card_students,parent,false));
    }
    @Override
    public void onBindViewHolder(@NonNull Adapter_show_students.ViewHolder holder, int position) {
      holder.name_student.setText(""+students.get(position).getLast_name());
      holder.rank.setText("%"+students.get(position).getRank());
      holder.cardView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              bundle.putString(CONSTANT.firstname_student,students.get(position).getFirst_name());
              bundle.putString(CONSTANT.lastname_student,students.get(position).getLast_name());
              bundle.putString(CONSTANT.bitrthdate_student,students.get(position).getBirthDate());
              bundle.putString(CONSTANT.username_student,students.get(position).getUsername());
              bundle.putInt(CONSTANT.class_student,students.get(position).getName_class());
              bundle.putInt(CONSTANT.Id_student,students.get(position).getSid());
              bundle.putInt(CONSTANT.section_student,students.get(position).getName_sec());
              bundle.putInt(CONSTANT.age_student,students.get(position).getAge());
              bundle.putInt(CONSTANT.attendance_student,students.get(position).getAttend_number());
              bundle.putInt(CONSTANT.absence_student,students.get(position).getAbsence_number());
              bundle.putInt(CONSTANT.rank_student,students.get(position).getRank());
              Intent intent = new Intent(holder.itemView.getContext(), StudentProfile.class);
              intent.putExtras(bundle);
              holder.itemView.getContext().startActivity(intent);
          }
      });
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rank , name_student;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_students);
            name_student = itemView.findViewById(R.id.name_student_section);
            rank = itemView.findViewById(R.id.rank_student_section);
        }
    }
}
