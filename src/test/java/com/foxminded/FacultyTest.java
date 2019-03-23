package com.foxminded;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class FacultyTest {

    @Test
    public void createGroup() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        Group createdGroup = faculty.createGroup("Group");
        int size = faculty.findGroups().size();
        assertEquals(1, size);

        long id = Helper.generateNewId();
        createdGroup.setId(id);
        Group foundGroup = faculty.findGroup(id);
        assertEquals(createdGroup, foundGroup);
    }

    @Test
    public void updateGroup() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("F1");
        Group createdGroup = faculty.createGroup("G1");
        long id = Helper.generateNewId();
        createdGroup.setId(id);
        faculty.updateGroup(id, "NEW");

        List<Group> foundGroups = faculty.findGroups();
        assertEquals("NEW", foundGroups.get(0).getName());
        int size = foundGroups.size();
        assertEquals(1, size);
    }

    @Test
    public void findGroup() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        Group group = faculty.createGroup("Group");
        long groupId = Helper.generateNewId();
        group.setId(groupId);

        Group foundGroup = faculty.findGroup(groupId);
        assertEquals("Group", foundGroup.getName());
    }

    @Test
    public void dismantleGroup() throws EntityNotFoundException, ValidationException{
        University university = new University();
        Faculty faculty = university.createFaculty("AM");
        Group group = faculty.createGroup("AM-81");
        int size = faculty.findGroups().size();
        assertEquals(1, size);

        long groupId = Helper.generateNewId();
        group.setId(groupId);
        faculty.dismantleGroup(groupId);
        size = faculty.findGroups().size();
        assertEquals(0, size);
    }

    @Test
    public void takeStudent() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        Group group = faculty.createGroup("Group");
        long groupId = Helper.generateNewId();
        group.setId(groupId);

        StudentCard takenStudent = faculty.takeStudent("John", groupId);
        long studentId = Helper.generateNewId();
        takenStudent.setId(studentId);

        StudentCard foundStudent = group.findStudent(studentId);
        assertEquals(takenStudent, foundStudent);
    }

    @Test
    public void changeStudentGroup() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("F1");
        Group group = faculty.createGroup("G1");
        long groupId = Helper.generateNewId();
        group.setId(groupId);

        StudentCard student = faculty.takeStudent("Igor", groupId);
        long newGroupId = Helper.generateNewId();
        group.setId(newGroupId);
        long studentId = Helper.generateNewId();
        student.setId(studentId);
        faculty.changeStudentGroup(studentId, newGroupId);

        StudentCard foundStudent = group.findStudent(studentId);
        assertEquals(newGroupId, foundStudent.getGroupId());
    }

    @Test
    public void findStudent() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        Group group = faculty.createGroup("G1");
        long groupId = Helper.generateNewId();
        group.setId(groupId);

        StudentCard student = faculty.takeStudent("Madonna", groupId);
        long studentId = Helper.generateNewId();
        student.setId(studentId);

        StudentCard foundStudent = group.findStudent(studentId);
        assertEquals("Madonna", foundStudent.getName());
    }

    @Test
    public void dismissStudent() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        Group group = faculty.createGroup("G1");
        long groupId = Helper.generateNewId();
        group.setId(groupId);

        StudentCard takenStudent = faculty.takeStudent("John", groupId);
        int size = faculty.findStudents().size();
        assertEquals(1, size);

        long studentId = Helper.generateNewId();
        takenStudent.setId(studentId);
        faculty.dismissStudent(studentId);
        size = faculty.findStudents().size();
        assertEquals(0, size);
    }

    @Test
    public void createSchedule() throws ValidationException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        Schedule schedule =  faculty.createSchedule();
        long scheduleId = Helper.generateNewId();
        schedule.setId(scheduleId);

        assertEquals(scheduleId, faculty.getSchedule().getId());
    }

    @Test(expected = ValidationException.class)
    public void removeSchedule() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        Schedule schedule =  faculty.createSchedule();
        long scheduleId = Helper.generateNewId();
        schedule.setId(scheduleId);

        faculty.removeSchedule(scheduleId);
        faculty.removeSchedule(scheduleId);
    }

    @Test
    public void hireMentor() throws ValidationException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        faculty.hireMentor("Mendeleev");
        int size = faculty.findMentors().size();
        assertEquals(1, size);
    }

    @Test
    public void findMentor() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        MentorCard mentor = faculty.hireMentor("Plank");
        long mentorId = Helper.generateNewId();
        mentor.setId(mentorId);

        MentorCard foundMentor = faculty.findMentor(mentorId);
        assertEquals("Plank", foundMentor.getName());
    }

    @Test
    public void fireMentor() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        MentorCard mentor = faculty.hireMentor("Mendeleev");
        int size = faculty.findMentors().size();
        assertEquals(1, size);

        long mentorId = Helper.generateNewId();
        mentor.setId(mentorId);

        faculty.fireMentor(mentorId);
        size = faculty.findMentors().size();
        assertEquals(0, size);
    }

    @Test
    public void addAuditorium() throws ValidationException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        faculty.addAuditorium(123);
        faculty.addAuditorium(124);
        int size = faculty.findAuditoria().size();
        assertEquals(2, size);
    }

    @Test
    public void findAuditorium() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        Auditorium auditorium = faculty.addAuditorium(123);
        long auditoriumId = Helper.generateNewId();
        auditorium.setId(auditoriumId);

        Auditorium foundAuditorium = faculty.findAuditorium(auditoriumId);
        assertEquals(123, foundAuditorium.getNumber());
    }

    @Test
    public void findAuditoria() throws ValidationException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        faculty.addAuditorium(123);
        faculty.addAuditorium(234);
        faculty.addAuditorium(345);
        int size = faculty.findAuditoria().size();
        assertEquals(3, size);
    }

    @Test
    public void addSubject() throws ValidationException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        faculty.addSubject("Math");
        int size = faculty.findSubjects().size();
        assertEquals(1, size);
    }

    @Test
    public void findSubject() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        Subject subject = faculty.addSubject("Math");
        long subjectId = Helper.generateNewId();
        subject.setId(subjectId);

        Subject foundSubject = faculty.findSubject(subjectId);
        assertEquals("Math", foundSubject.getName());
    }

    @Test
    public void findSubjects() throws ValidationException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        faculty.addSubject("English");
        faculty.addSubject("French");
        faculty.addSubject("Japan");
        int size = faculty.findSubjects().size();
        assertEquals(3, size);
    }

    @Test
    public void calculateAverageMark() throws ValidationException{
        University university = new University();
        Faculty faculty = university.createFaculty("Not any journal");
        double average = faculty.calculateAverageMark();
        double expected = 0;
        assertEquals(expected , average, 0.005);
    }

}
