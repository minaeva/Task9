package com.foxminded;

import java.util.UUID;

public class StudentCard {

    private UUID id;
    private String name;
    private UUID groupID;
    private UUID facultyID;

    public StudentCard(String name){
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public void setGroupID(UUID groupID) {
        this.groupID = groupID;
    }

    public UUID getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UUID getGroupID() {
        return groupID;
    }

    public UUID getFacultyID() {
        return facultyID;
    }

    public void dissmiss(){}
}
