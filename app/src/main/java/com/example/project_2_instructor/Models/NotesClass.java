package com.example.project_2_instructor.Models;

import java.util.ArrayList;

public class NotesClass {
    ArrayList<Integer> sections ;
    String title , exp_date , message;

    public NotesClass(ArrayList<Integer> sections, String title, String exp_date, String message) {
        this.sections = sections;
        this.title = title;
        this.exp_date = exp_date;
        this.message = message;
    }
}
