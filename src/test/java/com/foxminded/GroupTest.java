package com.foxminded;

import static org.junit.Assert.*;
import org.junit.Test;

public class GroupTest extends FillingUniversityWithData{

    @Test
    public void takeStudent() throws ValidationException, EntityNotFoundException{
        Faculty faculty = university.createFaculty("FFF1");
        Group group = faculty.createGroup("GG1");
        StudentCard student = new StudentCard("SS1");
        StudentCard takenStudent = group.takeStudent(student);

        long id = Helper.generateNewId();
        takenStudent.setId(id);
        StudentCard foundStudent = group.findStudent(id);
        assertEquals(takenStudent, foundStudent);
    }

    @Test
    public void findStudent() throws ValidationException, EntityNotFoundException{
        Faculty faculty = university.createFaculty("FFF2");
        Group group = faculty.createGroup("GG2");
        long groupId = Helper.generateNewId();
        group.setId(groupId);

        StudentCard student = new StudentCard("SS2");
        StudentCard takenStudent = group.takeStudent(student);
        long studentId = Helper.generateNewId();
        takenStudent.setId(studentId);

        StudentCard foundStudent = group.findStudent(studentId);
        assertEquals(takenStudent, foundStudent);
    }

    @Test
    public void dismissStudent() throws ValidationException, EntityNotFoundException{
        Faculty faculty = university.createFaculty("FFF3");
        Group group = faculty.createGroup("GG3");
        long groupId = Helper.generateNewId();
        group.setId(groupId);

        StudentCard student = faculty.takeStudent("SS3", groupId);
        long studentId = Helper.generateNewId();
        student.setId(studentId);
        int beforeSize = group.findStudents().size();
        group.dismissStudent(studentId);
        int afterSize = group.findStudents().size();
        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void findStudents() throws ValidationException, EntityNotFoundException{
        Faculty faculty = university.createFaculty("FFF4");
        Group group = faculty.createGroup("GG4");
        int beforeSize = group.findStudents().size();
        StudentCard student1 = new StudentCard("SS5");
        group.takeStudent(student1);
        StudentCard student2 = new StudentCard("SS5");
        group.takeStudent(student2);
        StudentCard student3 = new StudentCard("SS5");
        group.takeStudent(student3);

        int afterSize = group.findStudents().size();
        assertEquals(beforeSize + 3, afterSize);
    }
}
