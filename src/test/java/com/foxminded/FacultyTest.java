package com.foxminded;

import static org.junit.Assert.*;
import org.junit.Test;

public class FacultyTest {

    @Test
    public void addStudent_Ivan_correct() {
        String actual = "Ivan";
        Faculty faculty = new Faculty(actual);

        //IDs needed
/*        StudentCard studentCard = faculty.takeStudent(actual);
        System.out.println("Name " + studentCard.getName() + ", id " + studentCard.getId());
        assertEquals(studentCard.getName(), actual);
  */
    }
}
