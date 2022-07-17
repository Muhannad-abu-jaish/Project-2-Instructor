package com.example.project_2_instructor.Models;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project_2_instructor.Controller.AdapterParentsNote;

public class ParentsNotes {

    private int id ;
    private String message ;
    private String start_date ;
    private String username ;

    public ParentsNotes ()
    {

    }

    public ParentsNotes (int id , String message , String start_date , String username)
    {
        this.id = id ;
        this.message = message ;
        this.start_date = start_date ;
        this.username = username ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
