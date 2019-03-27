package com.foxminded;

import java.util.*;
import lombok.Data;

@Data
public class University {

    private long id;
    private String name;
    private List<Faculty> faculties = new ArrayList<>();

    public Faculty createFaculty(String facultyName){
        if (facultyName.equals("")) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        Helper.validateNameIsUnique(faculties, faculty -> faculty.getName().equals(facultyName), "Faculty", name);
        Faculty newFaculty = new Faculty(facultyName);
        faculties.add(newFaculty);
        return newFaculty;
    }

    public Faculty updateFaculty(String facultyName, String newName){
        Faculty faculty = findFaculty(facultyName);
        faculty.setName(newName);
        return faculty;
    }

    public boolean dismantleFaculty(String facultyName){
        return faculties.removeIf(faculty -> faculty.getName().equals(facultyName));
    }

    public Faculty findFaculty(String facultyName){
        return Helper.findObjectByNameIfExists(faculties, faculty -> faculty.getName().equals(facultyName), "Faculty", facultyName);
    }

    public double calculateAverageMark(){
        double result = 0.0;
        int counter = 0;
        for (Faculty faculty: faculties) {
            double midAverage = faculty.calculateAverageMark();
            if (midAverage != 0){
                result += midAverage;
                counter++;
            }
        }
        return result/counter;
    }
}
