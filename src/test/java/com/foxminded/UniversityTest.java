package com.foxminded;

import static org.junit.Assert.*;
import org.junit.Test;

public class UniversityTest extends FillingUniversityWithData {

    @Test
    public void createFaculty() throws ValidationException{
        int beforeSize = university.findFaculties().size();
        Faculty f1 = university.createFaculty("F1");
        long f1Id = Helper.generateNewId();
        f1.setId(f1Id);

        int afterSize = university.findFaculties().size();
        assertEquals(beforeSize + 1, afterSize);
    }

    @Test(expected = ValidationException.class)
    public void createFaculty_same_throwsException() throws ValidationException{
        Faculty f2 = university.createFaculty("F2");
        long f2Id = Helper.generateNewId();
        f2.setId(f2Id);
        university.createFaculty("F2");
    }

   @Test
    public void updateFaculty() throws ValidationException, EntityNotFoundException{
       int sizeBefore = university.findFaculties().size();
       Faculty f3 = university.createFaculty("F3");
       long f3Id = Helper.generateNewId();
       f3.setId(f3Id);
       university.updateFaculty(f3Id, "F3 NEW");

       Faculty foundFaculty = university.findFaculty(f3Id);
       assertEquals("F3 NEW", foundFaculty.getName());
       int sizeAfter = university.findFaculties().size();
       assertEquals(sizeBefore + 1, sizeAfter);
  }

    @Test(expected = EntityNotFoundException.class)
    public void updateFaculty_notExists_throwsException() throws EntityNotFoundException{
        long id = Helper.generateNewId();
        university.updateFaculty(id, "NEW");
    }

    @Test(expected = EntityNotFoundException.class)
    public void dismantleFaculty() throws EntityNotFoundException, ValidationException{
        Faculty f4 = university.createFaculty("F4");
        long f4Id = Helper.generateNewId();
        f4.setId(f4Id);
        university.dismantleFaculty(f4Id);
        university.findFaculty(f4Id);
    }

    @Test(expected = EntityNotFoundException.class)
    public void dismantleFaculty_notExists_throwsException() throws EntityNotFoundException{
        long id = Helper.generateNewId();
        university.updateFaculty(id, "NEW");
    }

    @Test
    public void findFaculty() throws ValidationException, EntityNotFoundException{
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
