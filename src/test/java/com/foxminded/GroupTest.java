package com.foxminded;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GroupTest{
    private University university;
    private Faculty faculty;
    private Group group;

    @Before
    public void before(){
        university = new University();
        faculty = university.createFaculty("FACULTY");
        group = faculty.createGroup("GROUP");
    }

    @Test
    public void takeStudent(){
        group.takeStudent(new StudentCard("S1"));

        assertEquals(1, group.getStudents().size());
    }

    @Test
    public void findStudent(){
        StudentCard takenStudent = group.takeStudent(new StudentCard("S2"));

        StudentCard foundStudent = group.findStudent("S2");

        assertEquals(takenStudent, foundStudent);
    }

    @Test
    public void dismissStudent(){
        group.takeStudent(new StudentCard("S3"));

        int beforeSize = group.getStudents().size();
        group.dismissStudent("S3");
        int afterSize = group.getStudents().size();

        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void getStudents(){
        int beforeSize = group.getStudents().size();

        StudentCard student1 = new StudentCard("S4");
        group.takeStudent(student1);
        StudentCard student2 = new StudentCard("S5");
        group.takeStudent(student2);
        StudentCard student3 = new StudentCard("S5");
        group.takeStudent(student3);

        int afterSize = group.getStudents().size();

        assertEquals(beforeSize + 3, afterSize);
    }
}
