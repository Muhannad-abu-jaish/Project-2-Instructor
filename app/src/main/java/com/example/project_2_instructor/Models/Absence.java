package com.example.project_2_instructor.Models;

import java.util.ArrayList;

public class Absence {

    ArrayList<AbsencesStudent>students_array;

    public Absence(ArrayList<AbsencesStudent> students_array) {
        this.students_array = students_array;
    }

    public ArrayList<AbsencesStudent> getStudents_array() {
        return students_array;
    }
}
