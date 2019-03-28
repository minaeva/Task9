package com.foxminded;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FacultyTest {
    private University university;
    private Faculty faculty;

    @Before
    public void before(){
        university = new University();
        faculty = university.createFaculty("FACULTY");
    }

    @Test
    public void createGroup() {
        faculty.createGroup("G1");
        assertEquals(1, faculty.getGroups().size());
    }

    @Test
    public void updateGroup() {
        faculty.createGroup("G2");
        faculty.updateGroup("G2", "NEW");

        Group foundGroup = faculty.findGroup("NEW");

        assertEquals("NEW", foundGroup.getName());
    }

    @Test
    public void findGroup(){
        faculty.createGroup("G3");

        Group foundGroup = faculty.findGroup("G3");

        assertEquals("G3", foundGroup.getName());
    }

    @Test
    public void dismantleGroup(){
        faculty.createGroup("G4");

        int beforeSize = faculty.getGroups().size();
        faculty.dismantleGroup("G4");
        int afterSize = faculty.getGroups().size();

        assertEquals(beforeSize - 1, afterSize);
    }

   @Test
    public void takeStudent(){
        Group group = faculty.createGroup("G5");
        StudentCard takenStudent = faculty.takeStudent("John", "G5");

        StudentCard foundStudent = group.findStudent(takenStudent.getName());

        assertEquals(takenStudent, foundStudent);
    }

    @Test
    public void changeStudentGroup(){
        Group group = faculty.createGroup("G6");
        faculty.takeStudent("Ben", "G6");

        faculty.changeStudentGroup("Ben", "G6");

        StudentCard foundStudent = group.findStudent("Ben");
        assertEquals(group.getName(), foundStudent.getGroupName());
    }

    @Test
    public void findStudent(){
        Group group = faculty.createGroup("G7");
        faculty.takeStudent("Alex", "G7");

        StudentCard foundStudent = group.findStudent("Alex");
        assertEquals("Alex", foundStudent.getName());
    }

    @Test
    public void dismissStudent(){
        faculty.createGroup("G8");
        faculty.takeStudent("Ira", "G8");

        int beforeSize = faculty.getAllStudents().size();
        faculty.dismissStudent("Ira");
        int afterSize = faculty.getAllStudents().size();

        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void createSchedule(){
        Schedule schedule = faculty.createSchedule();

        assertEquals(schedule, faculty.getSchedule());
    }

    @Test
    public void removeSchedule(){
        faculty.createSchedule();

        assertNotNull(faculty.getSchedule());

        faculty.clearSchedule();

        assertNull(faculty.getSchedule());
    }

   @Test
    public void hireMentor(){
        int beforeSize = faculty.getMentors().size();
        faculty.hireMentor("M1");
        int afterSize = faculty.getMentors().size();

        assertEquals(beforeSize + 1, afterSize);
    }

    @Test
    public void findMentor(){
        MentorCard mentor = faculty.hireMentor("M2");

        MentorCard foundMentor = faculty.findMentor(mentor.getName());

        assertEquals("M2", foundMentor.getName());
    }

    @Test
    public void fireMentor(){
        MentorCard mentor = faculty.hireMentor("M3");

        int beforeSize = faculty.getMentors().size();
        faculty.fireMentor(mentor.getName());
        int afterSize = faculty.getMentors().size();

        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void addAuditorium(){
        faculty.addAuditorium(11);
        faculty.addAuditorium(22);

        assertEquals(2, faculty.getAuditoria().size());
    }

    @Test
    public void findAuditorium(){
        Auditorium auditorium = faculty.addAuditorium(33);
        Auditorium foundAuditorium = faculty.findAuditorium(auditorium.getNumber());

        assertEquals(33, foundAuditorium.getNumber());
    }

    @Test
    public void findAuditoria(){
        int beforeSize = faculty.getAuditoria().size();
        faculty.addAuditorium(44);
        faculty.addAuditorium(55);
        faculty.addAuditorium(66);
        int afterSize = faculty.getAuditoria().size();

        assertEquals(beforeSize + 3, afterSize);
    }

    @Test
    public void removeAuditorium(){
        faculty.addAuditorium(77);

        int beforeSize = faculty.getAuditoria().size();
        faculty.removeAuditorium(77);
        int afterSize = faculty.getAuditoria().size();

        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void addSubject(){
        faculty.addSubject("S1");

        assertEquals(1, faculty.getSubjects().size());
    }

    @Test
    public void findSubject(){
        faculty.addSubject("S2");
        Subject foundSubject = faculty.findSubject("S2");

        assertEquals("S2", foundSubject.getName());
    }

    @Test
    public void removeSubject(){
        faculty.addSubject("French");
        int beforeSize = faculty.getSubjects().size();
        faculty.removeSubject("French");
        int afterSize = faculty.getSubjects().size();

        assertEquals(beforeSize - 1, afterSize);
    }
}
