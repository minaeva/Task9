package com.foxminded;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class UniversityTest {

    @Test
    public void createFaculty() throws ValidationException{
    University university = new University();
    university.createFaculty("IT");
    int size = university.findFaculties().size();
    assertEquals(1, size);

    university.createFaculty("AM");
    int secondSize = university.findFaculties().size();
    assertEquals(2, secondSize);
    }

    @Test(expected = ValidationException.class)
    public void createFaculty_same_throwsException() throws ValidationException{
        University university = new University();
        university.createFaculty("F1");
        university.createFaculty("F1");
    }

   @Test
    public void updateFaculty() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty1 = university.createFaculty("F1");
        long id = Helper.generateNewId();
        faculty1.setId(id);
        university.updateFaculty(id, "F1NEW");

        List<Faculty> foundFaculties = university.findFaculties();
        assertEquals("F1NEW", foundFaculties.get(0).getName());
        int size = university.findFaculties().size();
        assertEquals(1, size);

        Faculty faculty2 = university.createFaculty("F2");
        university.updateFaculty(id, "F1NEW2");
        size = university.findFaculties().size();
        assertEquals(2, size);
    }

    @Test(expected = EntityNotFoundException.class)
    public void updateFaculty_notExists_throwsException() throws EntityNotFoundException{
        University university = new University();
        long id = Helper.generateNewId();
        university.updateFaculty(id, "NEW");
    }

    @Test(expected = EntityNotFoundException.class)
    public void dismantleFaculty() throws EntityNotFoundException, ValidationException{
        University university = new University();
        Faculty createdFaculty = university.createFaculty("IASA");
        long id = Helper.generateNewId();
        createdFaculty.setId(id);
        university.dismantleFaculty(createdFaculty.getId());
        university.findFaculty(createdFaculty.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void dismantleFaculty_notExists_throwsException() throws EntityNotFoundException{
        University university = new University();
        long id = Helper.generateNewId();
        university.updateFaculty(id, "NEW");
    }

    @Test
    public void findFaculty() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty createdFaculty = university.createFaculty("CMP");
        long id = Helper.generateNewId();
        createdFaculty.setId(id);
        Faculty foundFaculty = university.findFaculty(createdFaculty.getId());
        assertEquals(createdFaculty.getName(), foundFaculty.getName());
    }

    @Test
    public void calculateAverageMark() throws ValidationException{
        University university = new University();
        university.createFaculty("1");
        university.createFaculty("2");
        university.createFaculty("3");
        university.createFaculty("4");
        double average = university.calculateAverageMark();
        double expected = 1.0;
        assertEquals(expected , average, 0.005);
    }

    private Faculty createStubFaculty(String name, long universityId){
        Faculty faculty = new Faculty(name);
        faculty.setName(name);
        faculty.setUniversityId(universityId);
        return faculty;
    }
}
