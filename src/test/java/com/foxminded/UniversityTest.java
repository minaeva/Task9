package com.foxminded;

import static org.junit.Assert.*;
import org.junit.Test;

public class UniversityTest extends FillingUniversityWithData {

    @Test
    public void createFaculty() throws IllegalArgumentException{
        int beforeSize = university.getFaculties().size();
        Faculty f1 = university.createFaculty("F1");
        long f1Id = Helper.generateNewId();
        f1.setId(f1Id);

        int afterSize = university.getFaculties().size();
        assertEquals(beforeSize + 1, afterSize);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createFaculty_same_throwsException() throws IllegalArgumentException{
        Faculty f2 = university.createFaculty("F2");
        long f2Id = Helper.generateNewId();
        f2.setId(f2Id);
        university.createFaculty("F2");
    }

   @Test
    public void updateFaculty() throws IllegalArgumentException{
       int sizeBefore = university.getFaculties().size();
       Faculty f3 = university.createFaculty("F3");
       long f3Id = Helper.generateNewId();
       f3.setId(f3Id);
       university.updateFaculty(f3Id, "F3 NEW");

       Faculty foundFaculty = university.findFaculty(f3Id);
       assertEquals("F3 NEW", foundFaculty.getName());
       int sizeAfter = university.getFaculties().size();
       assertEquals(sizeBefore + 1, sizeAfter);
  }

    @Test(expected = IllegalArgumentException.class)
    public void updateFaculty_notExists_throwsException() throws IllegalArgumentException{
        long id = Helper.generateNewId();
        university.updateFaculty(id, "NEW");
    }

    @Test(expected = IllegalArgumentException.class)
    public void dismantleFaculty() throws IllegalArgumentException{
        Faculty f4 = university.createFaculty("F4");
        long f4Id = Helper.generateNewId();
        f4.setId(f4Id);
        university.dismantleFaculty(f4Id);
        university.findFaculty(f4Id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void dismantleFaculty_notExists_throwsException() throws IllegalArgumentException{
        long id = Helper.generateNewId();
        university.updateFaculty(id, "NEW");
    }

    @Test
    public void findFaculty() throws IllegalArgumentException{
        Faculty f5 = university.createFaculty("F5");
        long f5Id = Helper.generateNewId();
        f5.setId(f5Id);
        Faculty foundFaculty = university.findFaculty(f5Id);
        assertEquals("F5", foundFaculty.getName());
    }

    @Test
    public void calculateAverageMark(){
        double average = university.calculateAverageMark();
        double expected = 7.875;
        assertEquals(expected, average, 0.005);
    }
}
