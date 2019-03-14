package com.foxminded;

import java.util.HashSet;
import java.util.Set;

public class Faculty {

    private int id;
    private String name;
    private Set<Group> groups = new HashSet<>();
    private Set<StudentCard> students = new HashSet<>();
    private static int counter = 0;

    public void dismantle(){}

    public Group createGroup(String name){
        if (groupExists(name)){
            System.out.println("Group already exists");
            return null;
        }
        Group group = new Group(name);
        group.setFacultyID(this.id);
        groups.add(group);
        return group;
    }

    public Group updateGroup(int groupID, String newName) {
        for (Group group: groups){
            if (group.getId() == groupID){
                group.setName(newName);
                return group;
            }
        }
        System.out.println("Cannot find group with id " + groupID);
        return null;
    }

    public void dismantleGroup(String name){}

    public Set<Group> findGroups(){
        return groups;
    }

    public StudentCard takeStudent(String name){
        if (studentExists(name) == true){
            System.out.println("Student already exists");
            return null;
        }
        StudentCard studentCard = new StudentCard(name);
        students.add(studentCard);
        return studentCard;
    }

     public StudentCard changeStudentGroup(int oldGroupID, int newGroupID){
        for (StudentCard studentCard: students){
            if(studentCard.getGroupID() == oldGroupID){
                studentCard.setGroupID(newGroupID);
                return studentCard;
            }
        }
        System.out.println("Cannot find student with groupId " + oldGroupID);
        return null;
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

    private boolean groupExists(String name){
        for (Group group:
             groups) {
            if (group.getName() == name){
                return true;
            }
        }
        return false;
    }

    private boolean studentExists(String name){
        for (StudentCard studentCard:
                students) {
            if (studentCard.getName() == name){
                return true;
            }
        }
        return false;
    }
}
