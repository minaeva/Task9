package com.foxminded;

import java.util.*;
import java.util.function.Predicate;
import lombok.Data;

@Data
public class University {

    private long id;
    private String name;
    private List<Faculty> faculties = new ArrayList<>();

    public Faculty createFaculty(String facultyName) throws IllegalArgumentException{
        if (facultyName.equals("")) throw new IllegalArgumentException("Name cannot be empty");
        Helper.validateNameIsUnique(faculties, faculty -> faculty.getName().equals(facultyName), "Faculty", name);
        Faculty newFaculty = new Faculty(facultyName);
        faculties.add(newFaculty);
        return newFaculty;
    }

    public Faculty updateFaculty(long facultyId, String newName) throws IllegalArgumentException{
        Faculty faculty = findFaculty(facultyId);
        faculty.setName(newName);
        return faculty;
    }

    public boolean dismantleFaculty(long facultyId) throws IllegalArgumentException{
        return faculties.removeIf(faculty1 -> faculty1.getId() == facultyId);
    }

    public Faculty findFaculty(long facultyId) throws IllegalArgumentException{
        return Helper.validateObjectExists(faculties, faculty -> faculty.getId() == facultyId, "Faculty", facultyId);
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
        if (result == 0) {
            return 0;
        }
        return result/counter;
    }
}
