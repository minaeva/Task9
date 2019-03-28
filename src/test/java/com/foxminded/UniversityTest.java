package com.foxminded;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UniversityTest{// extends FillingUniversityWithData {
    private University university;

    @Before
    public void before(){
        university = new University();
    }

    @Test
    public void createFaculty(){
        Faculty faculty = university.createFaculty("F1");
        assertEquals("F1", faculty.getName());
        assertEquals(1, university.getFaculties().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void createFaculty_same_throwsException(){
        university.createFaculty("F2");
        university.createFaculty("F2");
    }

   @Test
    public void updateFaculty(){
       university.createFaculty("F3");
       university.updateFaculty("F3", "F3 NEW");

       Faculty foundFaculty = university.findFaculty("F3 NEW");

       assertEquals("F3 NEW", foundFaculty.getName());
  }

    @Test(expected = IllegalArgumentException.class)
    public void updateFaculty_notExists_throwsException(){
        university.updateFaculty("NOT EXISTENT", "NEW");
    }

    @Test(expected = IllegalArgumentException.class)
    public void dismantleFaculty(){
        Faculty f4 = university.createFaculty("F4");
        university.dismantleFaculty("F4");

        university.findFaculty("F4");
    }

    @Test(expected = IllegalArgumentException.class)
    public void dismantleFaculty_notExists_throwsException(){
        university.updateFaculty("NOTHING", "NEW");
    }

    @Test
    public void findFaculty(){
        university.createFaculty("F5");
        Faculty foundFaculty = university.findFaculty("F5");
        assertEquals("F5", foundFaculty.getName());
    }
}
