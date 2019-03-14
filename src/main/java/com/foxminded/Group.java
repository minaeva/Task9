package com.foxminded;

import java.util.HashSet;
import java.util.Set;

public class Group {

    private int id = 0;
    private String name;
    private int facultyID;
    private Set<StudentCard> students = new HashSet<>();
    private static int counter = 0;

    public Group(String name){
        counter++;
        this.name = name;
        this.id = counter;
    }

    public void setFacultyID(int facultyID) {
        this.facultyID = facultyID;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getId(){
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public StudentCard takeStudent(StudentCard studentCard){
        students.add(studentCard);
        return studentCard;
    }

    public void dissmissStudent(int studentID){}

    public Set<StudentCard> findStudents(){
        return students;
    }

    public void dismantle(){}
}
