package com.example.project_2_instructor.Models;

import java.util.ArrayList;
import java.util.List;

public class ListAbsence {
     List<AbsenceNote> absenceNoteList;
    public static class AbsenceNote {
        int id ;
        boolean absence ;

        public AbsenceNote(int id, boolean absence) {
            this.id = id;
            this.absence = absence;
        }
    }



    public List<AbsenceNote> getAbsenceNoteList() {
        return absenceNoteList;
    }

    public void setAbsenceNoteList(List<AbsenceNote> absenceNoteList) {
        this.absenceNoteList = absenceNoteList;
    }
}
