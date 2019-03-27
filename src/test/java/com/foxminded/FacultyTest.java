package com.foxminded;

import static org.junit.Assert.*;
import org.junit.Test;

public class FacultyTest extends FillingUniversityWithData {

    @Test
    public void createGroup() {
        int beforeSize = faculty1.getGroups().size();
        Group createdGroup = faculty1.createGroup("G1");
        int afterSize = faculty1.getGroups().size();
        assertEquals(beforeSize + 1, afterSize);

        Group foundGroup = faculty1.findGroup("G1");
        assertEquals(createdGroup, foundGroup);
    }

    @Test
    public void updateGroup() {
        int beforeSize = faculty1.getGroups().size();
        faculty1.updateGroup(groupA.getName(), "NEW");
        Group foundGroup = faculty1.findGroup(groupA.getName());
        assertEquals("NEW", foundGroup.getName());
        int afterSize = faculty1.getGroups().size();
        assertEquals(beforeSize, afterSize);
    }

    @Test
    public void findGroup(){
        Group group = faculty1.createGroup("G3");
        Group foundGroup = faculty1.findGroup("G3");
        assertEquals("G3", foundGroup.getName());
    }

    @Test
    public void dismantleGroup(){
        int beforeSize = faculty1.getGroups().size();
        faculty1.dismantleGroup(groupA.getName());
        int afterSize = faculty1.getGroups().size();
        assertEquals(beforeSize - 1, afterSize);
    }

   @Test
    public void takeStudent(){
        StudentCard takenStudent = faculty1.takeStudent("John", groupB.getName());
        StudentCard foundStudent = groupB.findStudent(takenStudent.getName());
        assertEquals(takenStudent, foundStudent);
    }

    @Test
    public void changeStudentGroup(){
        faculty1.changeStudentGroup(student1A.getName(), groupB.getName());

        StudentCard foundStudent = groupB.findStudent(student1A.getName());
        assertEquals(groupB.getId(), foundStudent.getGroupId());
    }

    @Test
    public void findStudent(){
        StudentCard student = faculty1.takeStudent("Madonna", groupB.getName());
        long studentId = Helper.generateNewId();
        student.setId(studentId);

        StudentCard foundStudent = groupB.findStudent(studentId);
        assertEquals("Madonna", foundStudent.getName());
    }

    @Test
    public void dismissStudent(){
        int beforeSize = faculty1.findStudents().size();

        faculty1.dismissStudent(student1A.getName());
        int afterSize = faculty1.findStudents().size();
        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void createSchedule(){
        Schedule schedule = faculty2.createSchedule();
        long scheduleId = Helper.generateNewId();
        schedule.setId(scheduleId);
        assertEquals(scheduleId, faculty2.getSchedule().getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeSchedule(){
        Schedule schedule = faculty2.createSchedule();
        long scheduleId = Helper.generateNewId();
        schedule.setId(scheduleId);

        faculty2.removeSchedule(scheduleId);
        faculty2.removeSchedule(scheduleId);
    }

   @Test
    public void hireMentor(){
        int beforeSize = faculty1.getMentors().size();
        faculty1.hireMentor("M1");
        int afterSize = faculty1.getMentors().size();
        assertEquals(beforeSize + 1, afterSize);
    }

    @Test
    public void findMentor(){
        MentorCard mentor = faculty1.hireMentor("M2");

        MentorCard foundMentor = faculty1.findMentor(mentor.getName());
        assertEquals("M2", foundMentor.getName());
    }

    @Test
    public void fireMentor(){
        MentorCard mentor = faculty2.hireMentor("M3");
        int beforeSize = faculty2.getMentors().size();

        faculty2.fireMentor(mentor.getName());
        int afterSize = faculty2.getMentors().size();
        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void addAuditorium(){
        int beforeSize = faculty1.getAuditoria().size();
        faculty1.addAuditorium(11);
        faculty1.addAuditorium(22);
        int afterSize = faculty1.getAuditoria().size();
        assertEquals(beforeSize + 2, afterSize);
    }

    @Test
    public void findAuditorium(){
        Auditorium auditorium = faculty2.addAuditorium(33);
        Auditorium foundAuditorium = faculty2.findAuditorium(auditorium.getNumber());
        assertEquals(33, foundAuditorium.getNumber());
    }

    @Test
    public void findAuditoria(){
        int beforeSize = faculty1.getAuditoria().size();
        faculty1.addAuditorium(44);
        faculty1.addAuditorium(55);
        faculty1.addAuditorium(66);
        int afterSize = faculty1.getAuditoria().size();
        assertEquals(beforeSize + 3, afterSize);
    }

    @Test
    public void removeAuditorium(){
        int beforeSize = faculty1.getAuditoria().size();
        faculty1.removeAuditorium(auditorium.getNumber());
        int afterSize = faculty1.getAuditoria().size();
        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void addSubject(){
        int beforeSize = faculty1.getSubjects().size();
        faculty1.addSubject("S1");
        int afterSize = faculty1.getSubjects().size();
        assertEquals(beforeSize + 1, afterSize);
    }

    @Test
    public void findSubject(){
        Subject foundSubject = faculty1.findSubject(subjectMathF1.getName());
        assertEquals("Math", foundSubject.getName());
    }

    @Test
    public void removeSubject(){
        faculty1.addSubject("French");
        int beforeSize = faculty1.getSubjects().size();
        faculty1.removeSubject("French");
        int afterSize = faculty1.getSubjects().size();
        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void calculateAverageMark(){
        Faculty faculty = university.findFaculty(faculty1.getName());
        double average = faculty.calculateAverageMark();
        double expected = 9.75;
        assertEquals(expected, average, 0.005);
    }
}
