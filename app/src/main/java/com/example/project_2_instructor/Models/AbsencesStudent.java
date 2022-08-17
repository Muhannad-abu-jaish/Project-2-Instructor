package com.example.project_2_instructor.Models;

public class AbsencesStudent {

    int id ;
    boolean absence ;


    public AbsencesStudent(int id, boolean absence) {
        this.id = id;
        this.absence = absence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAbsence() {
        return absence;
    }

    public void setAbsence(boolean absence) {
        this.absence = absence;
    }
}
