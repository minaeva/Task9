package com.foxminded;

import java.util.*;
import java.util.function.Predicate;
import lombok.Data;

@Data
public class Faculty {

    private long id;
    private String name;
    private long universityId;
    private List<Group> groups = new ArrayList<>();
    private List<MentorCard> mentors = new ArrayList<>();
    private List<Subject> subjects = new ArrayList<>();
    private List<Auditorium> auditoria = new ArrayList<>();
    private List<Journal> journals = new ArrayList<>();
    private Schedule schedule;

    public Faculty(String name) {
        this.name = name;
    }

    public Group createGroup(String groupName) throws IllegalArgumentException{
        if (groupName.equals("")) throw new IllegalArgumentException("Name cannot be empty");
        Helper.validateNameIsUnique(groups, group -> group.getName().equals(groupName), "Group", groupName);
        Group newGroup = new Group(groupName);
        groups.add(newGroup);
        return newGroup;
    }

    public Group updateGroup(long groupId, String newName) throws IllegalArgumentException {
        Group group = findGroup(groupId);
        group.setName(newName);
        return group;
    }

    public boolean dismantleGroup(long groupId) throws IllegalArgumentException{
        return groups.removeIf(group1 -> group1.getId() == groupId);
    }

    public Group findGroup(long groupId) throws IllegalArgumentException{
        return Helper.validateObjectExists(groups, group -> group.getId() == groupId, "Group", groupId);
    }

    public StudentCard takeStudent(String studentName, long groupId) throws IllegalArgumentException{
        if (studentName.equals("")) throw new IllegalArgumentException("Name cannot be empty");
        Group group = findGroup(groupId);
        StudentCard newStudent = new StudentCard(studentName);
        group.takeStudent(newStudent);
        return newStudent;
    }

    public StudentCard changeStudentGroup(long studentId, long newGroupId) throws IllegalArgumentException{
        List<StudentCard> allStudents = findStudents();
        StudentCard student = Helper.validateObjectExists(allStudents, student1 -> student1.getId() == studentId, "Student", studentId);
        Group oldGroup = findGroup(student.getGroupId());
        oldGroup.dismissStudent(studentId);

        Group newGroup = findGroup(newGroupId);
        newGroup.takeStudent(student);
        return student;
    }

    public List<StudentCard> findStudents(){
        List<StudentCard> students = new ArrayList<>();
        for (Group group: groups){
            students.addAll(group.getStudents());
        }
        return students;
    }

    public void dismissStudent(long studentId){
        for (Group group: groups){
            try {
                List<StudentCard> students = new ArrayList<>();
                students.addAll(group.getStudents());
                StudentCard student = group.findStudent(studentId);
                group.dismissStudent(student.getId());
                break;
            }
            catch (IllegalArgumentException e){}
        }
    }

    public Schedule createSchedule() {
        Schedule newSchedule = new Schedule();
        this.schedule = newSchedule;
        return newSchedule;
    }

    public void removeSchedule(long scheduleId) throws IllegalArgumentException{
        if (this.schedule == null) {
            throw new IllegalArgumentException("NULL schedule is not accepted");
        }
        if (this.schedule.getId() != scheduleId) throw new IllegalArgumentException("Schedule with id " + scheduleId + " doesn't exist");
        this.schedule = null;
    }

    public MentorCard hireMentor(String mentorName) throws IllegalArgumentException{
        if (mentorName.equals("")) throw new IllegalArgumentException("Name cannot be empty");
        MentorCard newMentor = new MentorCard(mentorName);
        mentors.add(newMentor);
        return newMentor;
    }

    public MentorCard findMentor(long mentorId) throws IllegalArgumentException{
        return Helper.validateObjectExists(mentors, mentor -> mentor.getId() == mentorId, "Mentor", mentorId);
    }

    public boolean fireMentor(long mentorId) throws IllegalArgumentException{
        return mentors.removeIf(mentor1 -> mentor1.getId() == mentorId);
    }

    public Auditorium addAuditorium(int auditoriumNumber) throws IllegalArgumentException{
        if (auditoriumNumber <= 0) throw new IllegalArgumentException("Number should be positive");
        Auditorium auditorium = new Auditorium(auditoriumNumber);
        auditoria.add(auditorium);
        return auditorium;
    }

    public boolean removeAuditorium(long auditoriumId) throws IllegalArgumentException{
        return auditoria.removeIf(auditorium1 -> auditorium1.getId() == auditoriumId);
    }

    public Auditorium findAuditorium(long auditoriumId) throws IllegalArgumentException{
        return Helper.validateObjectExists(auditoria, auditorium -> auditorium.getId() == auditoriumId, "Auditorium", auditoriumId);
    }

    public Subject addSubject(String subjectName) throws IllegalArgumentException{
        if (subjectName.equals("")) throw new IllegalArgumentException("Subject cannot be empty");
        Subject subject = new Subject(subjectName);
        subjects.add(subject);
        return subject;
    }

    public boolean removeSubject(long subjectId) throws IllegalArgumentException{
        return subjects.removeIf(subject1 -> subject1.getId() == subjectId);
    }

    public Subject findSubject(long subjectId) throws IllegalArgumentException{
        return Helper.validateObjectExists(subjects, subject -> subject.getId() == subjectId, "Subject", subjectId);
    }

    public double calculateAverageMark() {
        if (groups.size() == 0) return 0;
        double result = 0.0;
        int counter = 0;
        for (Group group: groups) {
            double midAverage = group.getJournal().calculateAverageMark();
            if (midAverage != 0){
                result += midAverage;
                counter++;
            }
        }
        if (result == 0) return 0;
        return result/counter;
    }
}