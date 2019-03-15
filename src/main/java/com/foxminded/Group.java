package com.foxminded;

import java.util.*;

public class Group {

    private UUID id;
    private String name;
    private UUID facultyID;
    private Map<UUID, StudentCard> students = new HashMap<>();

    public Group(String name){
        this.id =  UUID.randomUUID();
        this.name = name;
    }

    public StudentCard takeStudent(StudentCard studentCard){
        students.put(studentCard.getID(), studentCard);
        return studentCard;
    }

    public void dissmissStudent(int studentID){}

    public ArrayList<StudentCard> findStudents(){
        return new ArrayList<StudentCard>(students.values());
    }

    public void dismantle(){}

    public UUID getId(){
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public UUID getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(UUID facultyID) {
        this.facultyID = facultyID;
    }
}
