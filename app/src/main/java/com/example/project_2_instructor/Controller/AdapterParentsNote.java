package com.example.project_2_instructor.Controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_instructor.Models.ParentsNotes;
import com.example.project_2_instructor.R;

import java.util.ArrayList;

public class AdapterParentsNote extends RecyclerView.Adapter<AdapterParentsNote.ViewHolderParentsNote> {

    LayoutInflater layoutInflater;
    ArrayList<ParentsNotes> parentsNotes = new ArrayList<>();

    public ArrayList<ParentsNotes> getParentsNotes() {
        return parentsNotes;
    }

    public void setParentsNotes(ArrayList<ParentsNotes> parentsNotes) {
        this.parentsNotes = parentsNotes;
    }

    public AdapterParentsNote (Context context , ArrayList<ParentsNotes> parentsNotes)
    {
        this.layoutInflater = LayoutInflater.from(context) ;
        this.parentsNotes = parentsNotes ;
    }

    @NonNull
    @Override
    //تستدعى في كل مرة يتم عرض عنصر على الشاشة لأول مرة
    public AdapterParentsNote.ViewHolderParentsNote onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parents_note_card_view , parent , false) ;
        return new ViewHolderParentsNote(view) ;
    }

    @Override
    //هو الذي يربط بين البيانات المراد عرضها كل مرة عن طريق دليل خاص بها وال view holder
    public void onBindViewHolder(@NonNull ViewHolderParentsNote holder, int position) {

        String message = parentsNotes.get(position).getMessage() ;
        String startDate = parentsNotes.get(position).getStart_date() ;
        String userName = parentsNotes.get(position).getUsername() ;

        holder.parentsNoteMessage.setText(message) ;
        holder.parentsNoteStartDate.setText(startDate) ;
        holder.parentsNoteUserName.setText(userName) ;

    }

    @Override
    public int getItemCount() {
        return parentsNotes.size();
    }


    public class ViewHolderParentsNote extends RecyclerView.ViewHolder {

        TextView parentsNoteMessage , parentsNoteStartDate , parentsNoteUserName ;
        public ViewHolderParentsNote(@NonNull View itemView) {
            super(itemView);

            parentsNoteMessage = itemView.findViewById(R.id.parents_note_card_view_content_tv) ;
            parentsNoteStartDate = itemView.findViewById(R.id.parents_note_card_view_time_tv) ;
            parentsNoteUserName = itemView.findViewById(R.id.parents_note_card_view_student_name_tv) ;
        }
    }
}
