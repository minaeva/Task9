package com.foxminded;

import static org.junit.Assert.*;
import org.junit.Test;

public class GroupTest {

    @Test
    public void takeStudent() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        Group group = faculty.createGroup("Group");
        StudentCard student = new StudentCard("Ted");
        StudentCard takenStudent = group.takeStudent(student);

        long id = Helper.generateNewId();
        takenStudent.setId(id);
        StudentCard foundStudent = group.findStudent(id);
        assertEquals(takenStudent, foundStudent);
    }

    @Test
    public void findStudent() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        Group group = faculty.createGroup("Group");
        long groupId = Helper.generateNewId();
        group.setId(groupId);

        StudentCard student = new StudentCard("Jed");
        StudentCard takenStudent = group.takeStudent(student);
        long studentId = Helper.generateNewId();
        takenStudent.setId(studentId);

        StudentCard foundStudent = group.findStudent(studentId);
        assertEquals(takenStudent, foundStudent);
    }

    @Test
    public void dismissStudent() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        Group group = faculty.createGroup("Group");
        long groupId = Helper.generateNewId();
        group.setId(groupId);

        StudentCard student = faculty.takeStudent("Bach", groupId);

        int size = group.findStudents().size();
        assertEquals(1, size);

        long studentId = Helper.generateNewId();
        student.setId(studentId);
        group.dismissStudent(studentId);
        size = group.findStudents().size();
        assertEquals(0, size);
    }

    @Test
    public void findStudents() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        Group group = faculty.createGroup("Group");
        StudentCard student1 = new StudentCard("Jed");
        group.takeStudent(student1);
        StudentCard student2 = new StudentCard("Jed");
        group.takeStudent(student2);
        StudentCard student3 = new StudentCard("Jed");
        group.takeStudent(student3);

        int size = group.findStudents().size();
        assertEquals(3, size);
    }
}
