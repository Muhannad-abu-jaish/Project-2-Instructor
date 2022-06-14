package com.example.project_2_instructor.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_instructor.Models.Section;
import com.example.project_2_instructor.R;
import com.example.project_2_instructor.View.Choice_students;
import com.example.project_2_instructor.View.MainInstructor;
import com.example.project_2_instructor.View.SectionsPage;

import java.util.ArrayList;

public class Adapter_show_sections extends RecyclerView.Adapter<Adapter_show_sections.ViewHolder> {

ArrayList<Section> sections = new ArrayList<>();
Activity activity;
    @NonNull
    @Override
    public Adapter_show_sections.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_sections
        ,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_show_sections.ViewHolder holder, int position) {
         holder.num_section.setText(""+sections.get(position).getName_sec());
         holder.num_students.setText(""+10); // Changed
        holder.cardSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*MainInstructor.redirectActivity(activity,Choice_students.class);*/
                Intent intent = new Intent(holder.itemView.getContext(),Choice_students.class);
                Bundle bundle = new Bundle();
                bundle.putInt("SectionID",sections.get(position).getId());
                intent.putExtras(bundle);
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sections.size();
    }

    public void setSections(Activity activity ,ArrayList<Section> sections) {
        this.sections = sections;
        this.activity = activity;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView num_students;
        TextView num_section;
        CardView cardSection;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            num_students = itemView.findViewById(R.id.count_student_section);
            num_section = itemView.findViewById(R.id.num_section);
            cardSection = itemView.findViewById(R.id.card_sections);
        }
    }
}
