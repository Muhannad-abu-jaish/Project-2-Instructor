package com.example.project_2_instructor.Controller;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_instructor.Models.AbsencesStudent;
import com.example.project_2_instructor.Models.Student;
import com.example.project_2_instructor.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterAbsenceFromDB extends RecyclerView.Adapter<AdapterAbsenceFromDB.AbsenceFromDBHolder> {

    ArrayList<Student> students = new ArrayList<>() ;
    ArrayList<AbsencesStudent> absencesStudents = new ArrayList<>() ;

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }


    public void sendToSQLite()
    {

    }
    @NonNull
    @Override
    public AbsenceFromDBHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_absence_students , parent , false) ;
        return new AdapterAbsenceFromDB.AbsenceFromDBHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull AbsenceFromDBHolder holder, @SuppressLint("RecyclerView") int position) {
        String name = students.get(position).getFirst_name() + " " + students.get(position).getFather_name()+ " "+ students.get(position).getLast_name() ;
        int id = students.get(position).getSid() ;
      //  boolean checked =holder.checkBox.isChecked() ;

        absencesStudents.add(new AbsencesStudent(id , false));

        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                absencesStudents.set(position , new AbsencesStudent(id , holder.checkBox.isChecked())); ;
            }
        });

        /*
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AbsencesStudent absencesStudent2 = new AbsencesStudent(secId ,name , holder.checkBox.isChecked());
                absencesStudents.s
                System.out.println("===================int setCheckBock +  holder.checkBox.isChecked() ");
            }
        });
        System.out.println("-----------------------"+name+"----------------------------");

         */
        holder.studentName.setText(name);

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public ArrayList<AbsencesStudent> getAbsencesStudents()
    {
        return absencesStudents ;
    }

    public class AbsenceFromDBHolder extends RecyclerView.ViewHolder{

        TextView studentName ;
        CheckBox checkBox ;
        public AbsenceFromDBHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.card_absence_name_tv) ;
            checkBox = itemView.findViewById(R.id.card_absence_check_box) ;
        }
    }
}
