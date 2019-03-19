package com.foxminded;

import java.util.UUID;

public class StudentCard {

    private UUID id;
    private String name;
    private UUID groupID;
    private UUID facultyID;

    public StudentCard(String name){
        this.name = name;
    }

    public void dissmiss(){
        //todo
    }

    public void setGroupID(UUID groupID) {
        this.groupID = groupID;
    }

    public UUID getGroupID() {
        return groupID;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFacultyID(UUID facultyID) {
        this.facultyID = facultyID;
    }

    public UUID getFacultyID() {
        return facultyID;
    }
}
