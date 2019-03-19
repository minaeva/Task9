package com.foxminded;

import java.util.List;
import java.util.UUID;

public class MentorCard {

    private UUID id;
    private String name;
    private UUID subjectID;
    private UUID facultyID;
    private List<Journal> journals;

    public MentorCard(){}

    public MentorCard(String name){
        this.name = name;
    }

    public void fire(){
        //todo
    }

    public void addMark(StudentCard studentCard, Subject subject, int mark){
        //todo
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(UUID subjectID) {
        this.subjectID = subjectID;
    }

    public UUID getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(UUID facultyID) {
        this.facultyID = facultyID;
    }
}
