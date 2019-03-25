package com.foxminded;

import static org.junit.Assert.*;
import org.junit.Test;

public class FacultyTest extends FillingUniversityWithData {

    @Test
    public void createGroup() throws IllegalArgumentException{
        int beforeSize = faculty1.getGroups().size();
        Group createdGroup = faculty1.createGroup("G1");
        int afterSize = faculty1.getGroups().size();
        assertEquals(beforeSize + 1, afterSize);

        long id = Helper.generateNewId();
        createdGroup.setId(id);
        Group foundGroup = faculty1.findGroup(id);
        assertEquals(createdGroup, foundGroup);
    }

    @Test
    public void updateGroup() throws IllegalArgumentException{
        int beforeSize = faculty1.getGroups().size();
        faculty1.updateGroup(groupA.getId(), "NEW");
        Group foundGroup = faculty1.findGroup(groupA.getId());
        assertEquals("NEW", foundGroup.getName());
        int afterSize = faculty1.getGroups().size();
        assertEquals(beforeSize, afterSize);
    }

    @Test
    public void findGroup() throws IllegalArgumentException{
        Group group = faculty1.createGroup("G3");
        long groupId = Helper.generateNewId();
        group.setId(groupId);
        Group foundGroup = faculty1.findGroup(groupId);
        assertEquals("G3", foundGroup.getName());
    }

    @Test
    public void dismantleGroup() throws IllegalArgumentException{
        int beforeSize = faculty1.getGroups().size();
        faculty1.dismantleGroup(groupA.getId());
        int afterSize = faculty1.getGroups().size();
        assertEquals(beforeSize - 1, afterSize);
    }

   @Test
    public void takeStudent() throws IllegalArgumentException{
        StudentCard takenStudent = faculty1.takeStudent("John", groupB.getId());
        long studentId = Helper.generateNewId();
        takenStudent.setId(studentId);

        StudentCard foundStudent = groupB.findStudent(studentId);
        assertEquals(takenStudent, foundStudent);
    }

    @Test
    public void changeStudentGroup() throws IllegalArgumentException{
        faculty1.changeStudentGroup(student1A.getId(), groupB.getId());

        StudentCard foundStudent = groupB.findStudent(student1A.getId());
        assertEquals(groupB.getId(), foundStudent.getGroupId());
    }

    @Test
    public void findStudent() throws IllegalArgumentException{
        StudentCard student = faculty1.takeStudent("Madonna", groupB.getId());
        long studentId = Helper.generateNewId();
        student.setId(studentId);

        StudentCard foundStudent = groupB.findStudent(studentId);
        assertEquals("Madonna", foundStudent.getName());
    }

    @Test
    public void dismissStudent() throws IllegalArgumentException{
        int beforeSize = faculty1.findStudents().size();

        faculty1.dismissStudent(student1A.getId());
        int afterSize = faculty1.findStudents().size();
        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void createSchedule() throws IllegalArgumentException{
        Schedule schedule = faculty2.createSchedule();
        long scheduleId = Helper.generateNewId();
        schedule.setId(scheduleId);
        assertEquals(scheduleId, faculty2.getSchedule().getId());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeSchedule() throws IllegalArgumentException{
        Schedule schedule = faculty2.createSchedule();
        long scheduleId = Helper.generateNewId();
        schedule.setId(scheduleId);

        faculty2.removeSchedule(scheduleId);
        faculty2.removeSchedule(scheduleId);
    }

   @Test
    public void hireMentor() throws IllegalArgumentException{
        int beforeSize = faculty1.getMentors().size();
        faculty1.hireMentor("M1");
        int afterSize = faculty1.getMentors().size();
        assertEquals(beforeSize + 1, afterSize);
    }

    @Test
    public void findMentor() throws IllegalArgumentException{
        MentorCard mentor = faculty1.hireMentor("M2");
        long mentorId = Helper.generateNewId();
        mentor.setId(mentorId);

        MentorCard foundMentor = faculty1.findMentor(mentorId);
        assertEquals("M2", foundMentor.getName());
    }

    @Test
    public void fireMentor() throws IllegalArgumentException{
        MentorCard mentor = faculty2.hireMentor("M3");
        int beforeSize = faculty2.getMentors().size();

        long mentorId = Helper.generateNewId();
        mentor.setId(mentorId);

        faculty2.fireMentor(mentorId);
        int afterSize = faculty2.getMentors().size();
        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void addAuditorium() throws IllegalArgumentException{
        int beforeSize = faculty1.getAuditoria().size();
        faculty1.addAuditorium(11);
        faculty1.addAuditorium(22);
        int afterSize = faculty1.getAuditoria().size();
        assertEquals(beforeSize + 2, afterSize);
    }

    @Test
    public void findAuditorium() throws IllegalArgumentException{
        Auditorium auditorium = faculty2.addAuditorium(33);
        long auditoriumId = Helper.generateNewId();
        auditorium.setId(auditoriumId);

        Auditorium foundAuditorium = faculty2.findAuditorium(auditoriumId);
        assertEquals(33, foundAuditorium.getNumber());
    }

    @Test
    public void findAuditoria() throws IllegalArgumentException{
        int beforeSize = faculty1.getAuditoria().size();
        faculty1.addAuditorium(44);
        faculty1.addAuditorium(55);
        faculty1.addAuditorium(66);
        int afterSize = faculty1.getAuditoria().size();
        assertEquals(beforeSize + 3, afterSize);
    }

    @Test
    public void removeAuditorium() throws IllegalArgumentException{
        int beforeSize = faculty1.getAuditoria().size();
        faculty1.removeAuditorium(auditorium.getId());
        int afterSize = faculty1.getAuditoria().size();
        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void addSubject() throws IllegalArgumentException{
        int beforeSize = faculty1.getSubjects().size();
        faculty1.addSubject("S1");
        int afterSize = faculty1.getSubjects().size();
        assertEquals(beforeSize + 1, afterSize);
    }

    @Test
    public void findSubject() throws IllegalArgumentException{
        Subject foundSubject = faculty1.findSubject(subjectMathF1.getId());
        assertEquals("Math", foundSubject.getName());
    }

    @Test
    public void removeSubject() throws IllegalArgumentException{
        int beforeSize = faculty1.getSubjects().size();
        faculty1.removeSubject(subjectEnglishF1.getId());
        int afterSize = faculty1.getSubjects().size();

        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void calculateAverageMark() throws IllegalArgumentException{
        Faculty faculty = university.findFaculty(faculty1.getId());
        double average = faculty.calculateAverageMark();
        double expected = 9.75;
        assertEquals(expected, average, 0.005);
    }
}
