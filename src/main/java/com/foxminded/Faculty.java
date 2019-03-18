package com.foxminded;

import java.util.*;

public class Faculty implements Cloneable {

    private UUID id;
    private String name;
    private UUID universityID;
    private Map<UUID, Group> groups;
    private Map<UUID, StudentCard> students;
    private Map<UUID, MentorCard> mentors;
    private Map<UUID, Subject> subjectss;
    private Map<UUID, Auditorium> auditoria;
    private Map<UUID, Journal> journals;// = new HashMap<>();

    public Faculty() {
    }

    public Faculty(String name) {
        this.name = name;
    }

    public Faculty(UUID id, String name, UUID universityID) {
        this.id = id;
        this.name = name;
        this.universityID = universityID;
    }

    public void dismantle() {
    }

    public Group createGroup(String name) {
        Group group = new Group(name);
        group.setFacultyID(this.id);
        groups.put(group.getId(), group);
        return group;
    }

    public Group updateGroup(UUID groupID, String newName) {
        if (!groups.containsKey(groupID)) {
            System.out.println("Cannot find group with ID " + groupID);
            return null;
        }
        Group group = groups.get(groupID);
        group.setName(newName);
        return group;
    }

    public void dismantleGroup(String name) {
    }

    public Map<UUID, Group> findGroups() {
        return groups;
    }

    public StudentCard takeStudent(String name) {
        StudentCard studentCard = new StudentCard(name);
        students.put(studentCard.getID(), studentCard);
        return studentCard;
    }

    public StudentCard changeStudentGroup(UUID studentID, UUID newGroupID) {
        if (!students.containsKey(studentID)) {
            System.out.println("Cannot find student with ID " + studentID);
            return null;
        }
        StudentCard studentCard = students.get(studentID);
        studentCard.setGroupID(newGroupID);
        return studentCard;
    }

    public void dissmissStudent(int studentID) {
    }

    public Schedule createScedule() {
        return new Schedule();
    }

    public void removeSchedule(int id) {
    }

    public MentorCard hireMentor(String name) {
        return new MentorCard();
    }

    public void fireMentor(int id) {
    }

    public double calculateAverageMark() {
        return 1.0;
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

    public UUID getUniversityID() {
        return universityID;
    }

    public void setUniversityID(UUID universityID) {
        this.universityID = universityID;
    }

    @Override
    public boolean equals(Object facultyToCheck) {
        if (facultyToCheck == this) return true;
        if (!(facultyToCheck instanceof Faculty)) return false;
        Faculty faculty = (Faculty) facultyToCheck;
        return faculty.getName().equals(name) && faculty.getId().equals(id);
    }

    @Override
    public Faculty clone() throws CloneNotSupportedException {
        try {
            return (Faculty) super.clone();
        } catch (ClassCastException e) {
            return new Faculty(this.getId(), this.getName(), this.getUniversityID());
        }
    }
}