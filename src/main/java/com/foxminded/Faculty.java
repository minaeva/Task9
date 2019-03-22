package com.foxminded;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class Faculty implements Cloneable {

    private long id;
    private String name;
    private long universityId;
    private List<Group> groups = new ArrayList<>();
    private List<StudentCard> students = new ArrayList<>();
    private List<MentorCard> mentors = new ArrayList<>();
    private List<Subject> subjects = new ArrayList<>();
    private List<Auditorium> auditoria = new ArrayList<>();
    private List<Journal> journals = new ArrayList<>();
    private Schedule schedule;

    public Faculty(String name) {
        this.name = name;
    }

    public void dismantle() {
        for (Group group : groups) {
            group.dismantle();
        }
        groups =  null;
        for (StudentCard student : students) {
            student.dismiss();
        }
        students = null;
        for (MentorCard mentor: mentors){
            mentor.fire();
        }
        mentors = null;
        for (Journal journal: journals){
            journal.dismantle();
        }
        journals = null;
    }

    public Group createGroup(String groupName) throws ValidationException{
        Predicate<Group> p = g -> g.getName().equals(groupName);
        if (groups.stream().anyMatch(p)) throw new ValidationException("Group with name " + groupName + " already exists");
        if (groupName.equals("")) throw new ValidationException("Name cannot be empty");
        Group newGroup = new Group(groupName);
        groups.add(newGroup);
        return newGroup;
    }

    public Group updateGroup(long groupId, String newName) throws EntityNotFoundException {
        Group group = findGroup(groupId);
        groups.remove(group);
        group.setName(newName);
        groups.add(group);
        return group;
    }

    public void dismantleGroup(long groupId) throws EntityNotFoundException{
        Group group = findGroup(groupId);
        groups.remove(group);
    }

    public Group findGroup(long groupId) throws EntityNotFoundException{
        Predicate<Group> p = g -> g.getId() == groupId;
        if (groups.stream().noneMatch(p)) throw new EntityNotFoundException("Group with id " + groupId + " doesn't exist");
        return groups.stream().filter(p).collect(Collectors.toList()).get(0);
    }

    public List<Group> findGroups() {
        return groups;
    }

 /*   public StudentCard takeStudent(String studentName) throws ValidationException{
        if (studentName.equals("")) throw new ValidationException("Name cannot be empty");
        StudentCard newStudent = new StudentCard(studentName);
        students.add(newStudent);
        return newStudent;
    }
    */

    public StudentCard takeStudent(String studentName, long groupId) throws EntityNotFoundException, ValidationException{
        if (studentName.equals("")) throw new ValidationException("Name cannot be empty");
        Group group = findGroup(groupId);
        StudentCard newStudent = new StudentCard(studentName);
        group.takeStudent(newStudent);
        students.add(newStudent);
        return newStudent;
    }

    public StudentCard findStudent(long studentId) throws EntityNotFoundException{
        Predicate<StudentCard> p = s -> s.getId() == studentId;
        if (students.stream().noneMatch(p)) throw new EntityNotFoundException("Student with id " + studentId + " doesn't exist");
        return students.stream().filter(p).collect(Collectors.toList()).get(0);
    }

    public StudentCard changeStudentGroup(long studentId, long newGroupId) throws EntityNotFoundException{
        StudentCard student = findStudent(studentId);
        students.remove(student);
        student.setGroupId(newGroupId);
        students.add(student);
        return student;
    }

    public List<StudentCard> findStudents(){
        return students;
    }

    public void dismissStudent(long studentId) throws EntityNotFoundException{
        StudentCard student = findStudent(studentId);
        students.remove(student);
    }

    public Schedule createSchedule() {
        Schedule newSchedule = new Schedule();
        this.schedule = newSchedule;
        return newSchedule;
    }

    public void removeSchedule(long scheduleId) throws EntityNotFoundException{
        if (this.schedule == null) throw new EntityNotFoundException("No schedule exists");
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
        Predicate<MentorCard> p = m -> m.getId() == mentorId;
        if (mentors.stream().noneMatch(p)) throw new EntityNotFoundException("Mentor with id " + mentorId + " doesn't exist");
        return mentors.stream().filter(p).collect(Collectors.toList()).get(0);
    }

    public List<MentorCard> findMentors(){
        return mentors;
    }

    public void fireMentor(long mentorId) throws EntityNotFoundException{
        MentorCard mentor = findMentor(mentorId);
        mentors.remove(mentor);
    }

    public Journal createJournal(long groupId){
        Journal journal = new Journal();
        journal.setGroupId(groupId);
        journal.setFacultyId(this.id);
        journals.add(journal);
        return journal;
    }

    public Journal findJournal(long journalId) throws EntityNotFoundException{
        Predicate<Journal> p = j -> j.getId() == journalId;
        if (journals.stream().noneMatch(p)) throw new EntityNotFoundException("Journal with id " + journalId + " doesn't exist");
        return journals.stream().filter(p).collect(Collectors.toList()).get(0);
    }

    public void removeJournal(long journalId) throws EntityNotFoundException{
        Journal journal = findJournal(journalId);
        journal.dismantle();
        journals.remove(journal);
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

    @Override
    public boolean equals(Object facultyToCheck) {
        if (facultyToCheck == this) return true;
        if (!(facultyToCheck instanceof Faculty)) return false;
        Faculty faculty = (Faculty) facultyToCheck;
        return faculty.getName().equals(name) && (faculty.getId() == id);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + (int)id;
        result = 31 * result + (int)universityId;
        return result;
    }

    @Override
    public Faculty clone() throws CloneNotSupportedException {
        try {
            return (Faculty) super.clone();
        } catch (ClassCastException e) {
            Faculty clonedFaculty = new Faculty(this.name);
            clonedFaculty.setId(this.id);
            clonedFaculty.setUniversityId(this.universityId);
            return clonedFaculty;
            }
    }
}