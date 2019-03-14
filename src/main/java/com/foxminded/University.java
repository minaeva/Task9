package com.foxminded;

import java.util.HashMap;
import java.util.Map;

public class University {
    private int id;
    private String name;
    private Map<Integer, Faculty> faculties = new HashMap<>();

    public Faculty createFaculty(String name){
        return new Faculty();
    }

    public Faculty updateFaculty(int id, String newName){
        Faculty faculty = faculties.get(id);
        faculty.setName(newName);
        return faculty;
    }

    public double calculateAverageMark(){ return 1.0; }


    public int getId(){
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
