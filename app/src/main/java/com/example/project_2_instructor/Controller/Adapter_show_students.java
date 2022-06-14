package com.example.project_2_instructor.Controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_instructor.Models.Student;
import com.example.project_2_instructor.R;

import java.util.ArrayList;

public class Adapter_show_students extends RecyclerView.Adapter<Adapter_show_students.ViewHolder> {
  ArrayList<Student>students = new ArrayList<>();

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
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView rank , name_student;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_student = itemView.findViewById(R.id.name_student_section);
            rank = itemView.findViewById(R.id.rank_student_section);
        }
    }
}
