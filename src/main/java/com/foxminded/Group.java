package com.foxminded;

import java.util.*;

public class Group {

    private UUID id;
    private String name;
    private UUID facultyID;
    private Map<UUID, StudentCard> students;

    public Group(){}

    public Group(String name){
        this.name = name;
    }

    public StudentCard takeStudent(StudentCard studentCard){
        students.put(studentCard.getId(), studentCard);
        studentCard.setGroupID(this.id);
        return studentCard;
    }

    public void dissmissStudent(UUID studentID){
        //todo
        students.remove(studentID);
    }

    public ArrayList<StudentCard> findStudents(){
        //todo
        return new ArrayList<>(students.values());
    }

    public void dismantle(){
        //todo
    }

    public UUID getId(){
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    @Override
    public boolean equals(Object groupToCheck){
        if (groupToCheck == this) return true;
        if (!(groupToCheck instanceof Group)) return false;
        Group group = (Group) groupToCheck;
        return group.getName().equals(name) && group.getId().equals(id);
    }

}
