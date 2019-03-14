package com.foxminded;

public class StudentCard {

    private int id = 0;
    private String name;
    private int groupID;
    private int facultyID;
    private static int counter = 0;

    public StudentCard(String name){
        counter++;
        this.name = name;
        this.id = counter;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getGroupID() {
        return groupID;
    }

    public int getFacultyID() {
        return facultyID;
    }

    public void dissmiss(){}
}
