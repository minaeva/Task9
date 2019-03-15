package com.foxminded;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class FacultyTest {

    @Test
    public void addStudent_Ivan_correct() {
        String actual = "Ivan";
        Faculty faculty = new Faculty(actual);
        StudentCard studentCard = faculty.takeStudent(actual);
        System.out.println("Name " + studentCard.getName() + ", id " + studentCard.getID());
        Assert.assertEquals(studentCard.getName(), actual);
    }

    @Test
    public void addStudent_IvanIvan_null() {
        String actual = "Ivan";
        Faculty faculty = new Faculty(actual);
        faculty.takeStudent(actual);
        StudentCard studentCard = faculty.takeStudent(actual);
        Assert.assertEquals(studentCard, null);
    }
}
