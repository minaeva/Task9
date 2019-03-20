package com.foxminded;

import lombok.Getter;
import lombok.Setter;
import java.util.*;

public class Faculty implements Cloneable {

    @Getter @Setter private long id;
    @Getter @Setter private String name;
    @Getter @Setter private long universityId;
    @Getter @Setter private Map<Long, Group> groups;
    @Getter @Setter private Map<Long, StudentCard> students;
    @Getter @Setter private Map<Long, MentorCard> mentors;
    @Getter @Setter private Map<Long, Subject> subjects;
    @Getter @Setter private Map<Long, Auditorium> auditoria;
    @Getter @Setter private Map<Long, Journal> journals;
    @Getter @Setter private Schedule schedule;

    public Faculty() { }

    public Faculty(String name) {
        this.name = name;
    }

    public Faculty(long id, String name, long universityId) {
        this.id = id;
        this.name = name;
        this.universityId = universityId;
    }

    public void dismantle() {
        //todo
    }

    public Group createGroup(String name) {
        Group group = new Group(name);
        group.setFacultyId(this.id);
        //?DAO to get id
        groups.put(group.getId(), group);
        return group;
    }

    public Group updateGroup(long groupId, String newName) {
        if (!groups.containsKey(groupId)) {
            System.out.println("Cannot find group with Id " + groupId);
            return null;
        }
        Group group = groups.get(groupId);
        group.setName(newName);
        return group;
    }

    public void dismantleGroup(String name) {
        //todo
    }

    public Map<Long, Group> findGroups() {
        return groups;
    }

    public StudentCard takeStudent(String name) {
        StudentCard studentCard = new StudentCard(name);
        studentCard.setFacultyId(this.id);
        //?DAO
        students.put(studentCard.getId(), studentCard);
        return studentCard;
    }

    public StudentCard changeStudentGroup(long studentId, long newGroupId) {
        if (!students.containsKey(studentId)) {
            System.out.println("Cannot find student with Id " + studentId);
            return null;
        }
        StudentCard studentCard = students.get(studentId);
        studentCard.setGroupId(newGroupId);
        return studentCard;
    }

    public void dissmissStudent(int studentId) {
        //todo
    }

    public Schedule createScedule() {
        Schedule newSchedule = new Schedule();
        newSchedule.setFacultyId(this.id);
        this.schedule = newSchedule;
        return newSchedule;
    }

    public void removeSchedule(int id) {
        //if id is correct
        this.schedule = null;
    }

    public MentorCard hireMentor(String name) {
        MentorCard newMentor = new MentorCard(name);
        newMentor.setFacultyId(this.id);
        mentors.put(newMentor.getId(),newMentor);
        return newMentor;
    }

    public void fireMentor(int id) {
        //todo
    }

    public double calculateAverageMark() {
        //todo
        return 1.0;
        }

    @Override
    public boolean equals(Object facultyToCheck) {
        if (facultyToCheck == this) return true;
        if (!(facultyToCheck instanceof Faculty)) return false;
        Faculty faculty = (Faculty) facultyToCheck;
        return faculty.getName().equals(name) && (faculty.getId() == id);
    }

    @Override
    public Faculty clone() throws CloneNotSupportedException {
        try {
            return (Faculty) super.clone();
        } catch (ClassCastException e) {
            return new Faculty(this.getId(), this.getName(), this.getUniversityId());
        }
    }
}