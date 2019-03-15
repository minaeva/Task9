package com.foxminded;

import java.util.*;

public class Faculty {

    private UUID id;
    private String name;
    private UUID univerityID;
    private Map<UUID, Group> groups = new HashMap<>();
    private Map<UUID, StudentCard> students = new HashMap<>();
    private Map<UUID, MentorCard> mentors = new HashMap<>();
    private Map<UUID, Subject> subjectss = new HashMap<>();
    private Map<UUID, Auditorium> auditoria = new HashMap<>();
    private Map<UUID, Journal> journals = new HashMap<>();

    public Faculty(String name){
        this.id =  UUID.randomUUID();
        this.name = name;
    }

    public void dismantle(){}

    public Group createGroup(String name){
        Group group = new Group(name);
        group.setFacultyID(this.id);
        groups.put(group.getId(), group);
        return group;
    }

    public Group updateGroup(int groupID, String newName) {
        if (! groups.containsKey(groupID)) {
            System.out.println("Cannot find group with ID " + groupID);
            return null;
        }
        Group group = groups.get(groupID);
                group.setName(newName);
                return group;
    }

    public void dismantleGroup(String name){}

    public Map<UUID, Group> findGroups(){
        return groups;
    }

    public StudentCard takeStudent(String name){
        StudentCard studentCard = new StudentCard(name);
        students.put(studentCard.getID(), studentCard);
        return studentCard;
    }

    public StudentCard changeStudentGroup(UUID studentID, UUID newGroupID){
        if (! students.containsKey(studentID)) {
            System.out.println("Cannot find student with ID " + studentID);
            return null;
        }
        StudentCard studentCard = students.get(studentID);
        studentCard.setGroupID(newGroupID);
                return studentCard;
    }

    public void dissmissStudent(int studentID){}

    public Schedule createScedule(){
        return new Schedule();
    }

    public void removeSchedule(int id){}

    public MentorCard hireMentor(String name){
        return new MentorCard();
    }

    public void fireMentor(int id){}

    public double calculateAverageMark(){ return 1.0; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUniverityID() {
        return univerityID;
    }

    public void setUniverityID(UUID univerityID) {
        this.univerityID = univerityID;
    }
}
