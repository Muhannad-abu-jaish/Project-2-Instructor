package com.example.project_2_instructor.Models;

public class Section {
    int id , name_sec ,classeNameClass , num_students;

    public Section(int id, int name_sec, int classeNameClass, int num_students) {
        this.id = id;
        this.name_sec = name_sec;
        this.classeNameClass = classeNameClass;
        this.num_students = num_students;
    }
    public int getId() {
        return id;
    }

    public int getName_sec() {
        return name_sec;
    }

    public int getClasseNameClass() {
        return classeNameClass;
    }

    public int getNum_students() {
        return num_students;
    }
}
