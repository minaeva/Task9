package com.foxminded;

import java.util.*;
import java.util.function.Predicate;
import lombok.Data;

@Data
public class Faculty implements Cloneable {

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

    public Group createGroup(String groupName) throws ValidationException{
        if (groupName.equals("")) throw new ValidationException("Name cannot be empty");
        Predicate<Group> p = group -> group.getName().equals(groupName);
        Helper.validateIfNew(groups, p, "Group", groupName);
        Group newGroup = new Group(groupName);
        groups.add(newGroup);
        return newGroup;
    }

    public Group updateGroup(long groupId, String newName) throws EntityNotFoundException {
        Group group = findGroup(groupId);
        group.setName(newName);
        return group;
    }

    public void dismantleGroup(long groupId) throws EntityNotFoundException{
        Group group = findGroup(groupId);
        groups.remove(group);
    }

    public Group findGroup(long groupId) throws EntityNotFoundException{
        Predicate<Group> p = group -> group.getId() == groupId;
        return Helper.validateIfExists(groups, p, "Group", groupId);
    }

    public List<Group> findGroups() {
        return groups;
    }

    public StudentCard takeStudent(String studentName, long groupId) throws EntityNotFoundException, ValidationException{
        if (studentName.equals("")) throw new ValidationException("Name cannot be empty");
        Group group = findGroup(groupId);
        StudentCard newStudent = new StudentCard(studentName);
        group.takeStudent(newStudent);
        return newStudent;
    }

    public StudentCard changeStudentGroup(long studentId, long newGroupId) throws EntityNotFoundException{
        List<StudentCard> allStudents = findStudents();
        Predicate<StudentCard> p = student -> student.getId() == studentId;
        StudentCard student = Helper.validateIfExists(allStudents, p, "Student", studentId);
        student.setGroupId(newGroupId);
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
            catch (EntityNotFoundException e){}
        }
    }

    public Schedule createSchedule() {
        Schedule newSchedule = new Schedule();
        this.schedule = newSchedule;
        return newSchedule;
    }

    public void removeSchedule(long scheduleId) throws ValidationException, EntityNotFoundException{
        if (this.schedule == null) {
            throw new ValidationException("NULL schedule is not accepted");
        }
        if (this.schedule.getId() != scheduleId) throw new EntityNotFoundException("Schedule with id " + scheduleId + " doesn't exist");
        this.schedule = null;
    }

    public MentorCard hireMentor(String mentorName) throws ValidationException{
        if (mentorName.equals("")) throw new ValidationException("Name cannot be empty");
        MentorCard newMentor = new MentorCard(mentorName);
        mentors.add(newMentor);
        return newMentor;
    }

    public MentorCard findMentor(long mentorId) throws EntityNotFoundException{
        Predicate<MentorCard> p = mentor -> mentor.getId() == mentorId;
        return Helper.validateIfExists(mentors, p, "Mentor", mentorId);
    }

    public List<MentorCard> findMentors(){
        return mentors;
    }

    public void fireMentor(long mentorId) throws EntityNotFoundException{
        MentorCard mentor = findMentor(mentorId);
        mentors.remove(mentor);
    }

    public Auditorium addAuditorium(int auditoriumNumber) throws ValidationException{
        if (auditoriumNumber <= 0) throw new ValidationException("Number should be positive");
        Auditorium auditorium = new Auditorium(auditoriumNumber);
        auditoria.add(auditorium);
        return auditorium;
    }

    public void removeAuditorium(long auditoriumId) throws EntityNotFoundException{
        Auditorium auditorium = findAuditorium(auditoriumId);
        auditoria.remove(auditorium);
    }

    public Auditorium findAuditorium(long auditoriumId) throws EntityNotFoundException{
        Predicate<Auditorium> p = auditorium -> auditorium.getId() == auditoriumId;
        return Helper.validateIfExists(auditoria, p, "Auditorium", auditoriumId);
    }

    public List<Auditorium> findAuditoria(){
        return auditoria;
    }

    public Subject addSubject(String subjectName) throws ValidationException{
        if (subjectName.equals("")) throw new ValidationException("Subject cannot be empty");
        Subject subject = new Subject(subjectName);
        subjects.add(subject);
        return subject;
    }

    public void removeSubject(long subjectId) throws EntityNotFoundException{
        Subject subject = findSubject(subjectId);
        subjects.remove(subject);
    }

    public Subject findSubject(long subjectId) throws EntityNotFoundException{
        Predicate<Subject> p = subject -> subject.getId() == subjectId;
        return Helper.validateIfExists(subjects, p, "Subject", subjectId);
    }

    public List<Subject> findSubjects(){
        return subjects;
    }

    public double calculateAverageMark() {
        if (journals.size() == 0) return 0;
        double result = 0.0;
        int counter = 0;
        for (Journal journal: journals) {
            result += journal.calculateAverageMark();
            counter++;
        }
        return result/counter;
    }
}