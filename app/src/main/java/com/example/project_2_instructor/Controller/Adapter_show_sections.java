package com.example.project_2_instructor.Controller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_instructor.Models.Section;
import com.example.project_2_instructor.R;

import java.util.ArrayList;

public class Adapter_show_sections extends RecyclerView.Adapter<Adapter_show_sections.ViewHolder> {

ArrayList<Section> sections = new ArrayList<>();
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
    }

    @Override
    public int getItemCount() {
        return sections.size();
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView num_students;
        TextView num_section;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            num_students = itemView.findViewById(R.id.count_student_section);
            num_section = itemView.findViewById(R.id.num_section);
        }
    }
}
