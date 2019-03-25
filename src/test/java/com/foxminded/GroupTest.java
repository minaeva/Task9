package com.foxminded;

import static org.junit.Assert.*;
import org.junit.Test;

public class GroupTest extends FillingUniversityWithData{

    @Test
    public void takeStudent() throws IllegalArgumentException{
        StudentCard student = new StudentCard("SS1");
        StudentCard takenStudent = groupA.takeStudent(student);
        long id = Helper.generateNewId();
        takenStudent.setId(id);

        StudentCard foundStudent = groupA.findStudent(id);
        assertEquals(takenStudent, foundStudent);
    }

    @Test
    public void findStudent() throws IllegalArgumentException{
        StudentCard student = new StudentCard("SS2");
        StudentCard takenStudent = groupA.takeStudent(student);
        long studentId = Helper.generateNewId();
        takenStudent.setId(studentId);

        StudentCard foundStudent = groupA.findStudent(studentId);
        assertEquals(takenStudent, foundStudent);
    }

    @Test
    public void dismissStudent() throws IllegalArgumentException{
        int beforeSize = groupA.getStudents().size();
        groupA.dismissStudent(student1A.getId());
        int afterSize = groupA.getStudents().size();
        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void getStudents() throws IllegalArgumentException{
        int beforeSize = groupB.getStudents().size();
        StudentCard student1 = new StudentCard("SS5");
        groupB.takeStudent(student1);
        StudentCard student2 = new StudentCard("SS5");
        groupB.takeStudent(student2);
        StudentCard student3 = new StudentCard("SS5");
        groupB.takeStudent(student3);

        int afterSize = groupB.getStudents().size();
        assertEquals(beforeSize + 3, afterSize);
    }
}
