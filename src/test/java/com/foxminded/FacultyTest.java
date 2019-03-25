package com.foxminded;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class FacultyTest extends FillingUniversityWithData {

    @Test
    public void createGroup() throws ValidationException, EntityNotFoundException{
    //    University university = new University();
        Faculty faculty = university.createFaculty("FF1");
        int beforeSize = faculty.findGroups().size();
        Group createdGroup = faculty.createGroup("G1");
        int afterSize = faculty.findGroups().size();
        assertEquals(beforeSize + 1, afterSize);

        long id = Helper.generateNewId();
        createdGroup.setId(id);
        Group foundGroup = faculty.findGroup(id);
        assertEquals(createdGroup, foundGroup);
    }

    @Test
    public void updateGroup() throws ValidationException, EntityNotFoundException{
        Faculty faculty = university.createFaculty("FF2");
        Group createdGroup = faculty.createGroup("G2");
        long groupId = Helper.generateNewId();
        createdGroup.setId(groupId);
        int beforeSize = faculty.findGroups().size();

        faculty.updateGroup(groupId, "NEW");
        Group foundGroup = faculty.findGroup(groupId);
        assertEquals("NEW", foundGroup.getName());
        int afterSize = faculty.findGroups().size();
        assertEquals(beforeSize, afterSize);
    }

    @Test
    public void findGroup() throws ValidationException, EntityNotFoundException{
        Faculty faculty = university.createFaculty("FF3");
        Group group = faculty.createGroup("G3");
        long groupId = Helper.generateNewId();
        group.setId(groupId);
        Group foundGroup = faculty.findGroup(groupId);
        assertEquals("G3", foundGroup.getName());
    }

    @Test
    public void dismantleGroup() throws EntityNotFoundException, ValidationException{
        Faculty faculty = university.createFaculty("FF4");
        Group group = faculty.createGroup("G4");
        int beforeSize = faculty.findGroups().size();
        long groupId = Helper.generateNewId();
        group.setId(groupId);
        faculty.dismantleGroup(groupId);
        int afterSize = faculty.findGroups().size();
        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void takeStudent() throws ValidationException, EntityNotFoundException{
        Faculty faculty = university.createFaculty("FF5");
        Group group = faculty.createGroup("G5");
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
        Faculty faculty = university.createFaculty("FF6");
        Group group = faculty.createGroup("G6");
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
        Faculty faculty = university.createFaculty("FF7");
        Group group = faculty.createGroup("G7");
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
        Faculty faculty = university.createFaculty("FF8");
        Group group = faculty.createGroup("G8");
        long groupId = Helper.generateNewId();
        group.setId(groupId);

        StudentCard takenStudent = faculty.takeStudent("John", groupId);
        int beforeSize = faculty.findGroups().size();

        long studentId = Helper.generateNewId();
        takenStudent.setId(studentId);
        faculty.dismissStudent(studentId);
        int afterSize = faculty.findStudents().size();
        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void createSchedule() throws ValidationException{
        Faculty faculty = university.createFaculty("FF9");
        Schedule schedule = faculty.createSchedule();
        long scheduleId = Helper.generateNewId();
        schedule.setId(scheduleId);

        assertEquals(scheduleId, faculty.getSchedule().getId());
    }

    @Test(expected = ValidationException.class)
    public void removeSchedule() throws ValidationException, EntityNotFoundException{
        Faculty faculty = university.createFaculty("FF10");
        Schedule schedule = faculty.createSchedule();
        long scheduleId = Helper.generateNewId();
        schedule.setId(scheduleId);

        faculty.removeSchedule(scheduleId);
        faculty.removeSchedule(scheduleId);
    }

    @Test
    public void hireMentor() throws ValidationException{
        Faculty faculty = university.createFaculty("FF11");
        int beforeSize = faculty.findGroups().size();
        faculty.hireMentor("M1");
        int afterSize = faculty.findMentors().size();
        assertEquals(beforeSize + 1, afterSize);
    }

    @Test
    public void findMentor() throws ValidationException, EntityNotFoundException{
        Faculty faculty = university.createFaculty("FF12");
        MentorCard mentor = faculty.hireMentor("M2");
        long mentorId = Helper.generateNewId();
        mentor.setId(mentorId);

        MentorCard foundMentor = faculty.findMentor(mentorId);
        assertEquals("M2", foundMentor.getName());
    }

    @Test
    public void fireMentor() throws ValidationException, EntityNotFoundException{
        Faculty faculty = university.createFaculty("FF13");
        MentorCard mentor = faculty.hireMentor("M3");
        int beforeSize = faculty.findMentors().size();

        long mentorId = Helper.generateNewId();
        mentor.setId(mentorId);

        faculty.fireMentor(mentorId);
        int afterSize = faculty.findMentors().size();
        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void addAuditorium() throws ValidationException{
        Faculty faculty = university.createFaculty("FF14");
        int beforeSize = faculty.findAuditoria().size();
        faculty.addAuditorium(11);
        faculty.addAuditorium(22);
        int afterSize = faculty.findAuditoria().size();
        assertEquals(beforeSize + 2, afterSize);
    }

    @Test
    public void findAuditorium() throws ValidationException, EntityNotFoundException{
        Faculty faculty = university.createFaculty("FF15");
        Auditorium auditorium = faculty.addAuditorium(33);
        long auditoriumId = Helper.generateNewId();
        auditorium.setId(auditoriumId);

        Auditorium foundAuditorium = faculty.findAuditorium(auditoriumId);
        assertEquals(33, foundAuditorium.getNumber());
    }

    @Test
    public void findAuditoria() throws ValidationException{
        Faculty faculty = university.createFaculty("FF16");
        int beforeSize = faculty.findAuditoria().size();
        faculty.addAuditorium(44);
        faculty.addAuditorium(55);
        faculty.addAuditorium(66);
        int afterSize = faculty.findAuditoria().size();
        assertEquals(beforeSize + 3, afterSize);
    }

    @Test
    public void addSubject() throws ValidationException{
        Faculty faculty = university.createFaculty("FF17");
        int beforeSize = faculty.findSubjects().size();
        faculty.addSubject("S1");
        int afterSize = faculty.findSubjects().size();
        assertEquals(beforeSize + 1, afterSize);
    }

    @Test
    public void findSubject() throws ValidationException, EntityNotFoundException{
        Faculty faculty = university.createFaculty("FF18");
        Subject subject = faculty.addSubject("S2");
        long subjectId = Helper.generateNewId();
        subject.setId(subjectId);
        Subject foundSubject = faculty.findSubject(subjectId);
        assertEquals("S2", foundSubject.getName());
    }

    @Test
    public void findSubjects() throws ValidationException{
        Faculty faculty = university.createFaculty("FF19");
        int beforeSize = faculty.findSubjects().size();
        faculty.addSubject("S3");
        faculty.addSubject("S4");
        faculty.addSubject("S5");
        int afterSize = faculty.findSubjects().size();
        assertEquals(beforeSize + 3, afterSize);
    }

    @Test
    public void calculateAverageMark() throws EntityNotFoundException{
        Faculty faculty = university.findFaculty(faculty1Id);
        double average = faculty.calculateAverageMark();
        double expected = 0;
        assertEquals(expected, average, 0.005);
    }

}
