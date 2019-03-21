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

        long id = IdGenerator.newId();
        createdGroup.setId(id);
        Group foundGroup = faculty.findGroup(id);
        assertEquals(createdGroup, foundGroup);
    }

    @Test
    public void updateGroup() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("F1");
        Group createdGroup = faculty.createGroup("G1");
        long id = IdGenerator.newId();
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
        long groupId = IdGenerator.newId();
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

        long groupId = IdGenerator.newId();
        group.setId(groupId);
        faculty.dismantleGroup(groupId);
        size = faculty.findGroups().size();
        assertEquals(0, size);
    }

    @Test
    public void takeStudent() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty createdFaculty = university.createFaculty("Faculty");
        StudentCard takenStudent = createdFaculty.takeStudent("John");
        long id = IdGenerator.newId();
        takenStudent.setId(id);
        StudentCard foundStudent = createdFaculty.findStudent(id);
        assertEquals(takenStudent, foundStudent);
    }

    @Test
    public void changeStudentGroup() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("F1");
        Group group = faculty.createGroup("G1");
        StudentCard student = faculty.takeStudent("Igor");
        long newGroupId = IdGenerator.newId();
        group.setId(newGroupId);
        long studentId = IdGenerator.newId();
        student.setId(studentId);
        faculty.changeStudentGroup(studentId, newGroupId);

        StudentCard foundStudent = faculty.findStudent(studentId);
        assertEquals(newGroupId, foundStudent.getGroupId());
    }

    @Test
    public void findStudent() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        StudentCard student = faculty.takeStudent("Madonna");
        long studentId = IdGenerator.newId();
        student.setId(studentId);

        StudentCard foundStudent = faculty.findStudent(studentId);
        assertEquals("Madonna", foundStudent.getName());
    }

    @Test
    public void dismissStudent() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        StudentCard takenStudent = faculty.takeStudent("John");
        int size = faculty.findStudents().size();
        assertEquals(1, size);

        long studentId = IdGenerator.newId();
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
        long scheduleId = IdGenerator.newId();
        schedule.setId(scheduleId);

        assertEquals(scheduleId, faculty.getSchedule().getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void removeSchedule() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        Schedule schedule =  faculty.createSchedule();
        long scheduleId = IdGenerator.newId();
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
        long mentorId = IdGenerator.newId();
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

        long mentorId = IdGenerator.newId();
        mentor.setId(mentorId);

        faculty.fireMentor(mentorId);
        size = faculty.findMentors().size();
        assertEquals(0, size);
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
