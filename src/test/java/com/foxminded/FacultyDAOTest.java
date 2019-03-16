package com.foxminded;

import com.foxminded.dao.FacultyDAO;
import com.foxminded.dao.FacultyDAOImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;

public class FacultyDAOTest {

    private static FacultyDAO facultyDAO;

    @BeforeClass
    public static void initFacultyDAO(){
        facultyDAO = new FacultyDAOImpl();
    }

    @Test
    public void save() throws ValidationException, CloneNotSupportedException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("AM", universityID);

        Faculty savedFaculty = facultyDAO.save(facultyToSave);

        Faculty receivedFaculty = facultyDAO.findByID(savedFaculty.getId());

        Faculty expected = createStubFaculty("AM", universityID);
        expected.setId(savedFaculty.getId());

        Assert.assertNotNull(savedFaculty.getId());
        Assert.assertEquals(expected, savedFaculty);
        Assert.assertEquals(expected, receivedFaculty);
    }

    @Test
    public void save_idsAreDifferent() throws ValidationException, CloneNotSupportedException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("IT", universityID);

        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        Faculty receivedFaculty = facultyDAO.findByID(savedFaculty.getId());

        Faculty expected = createStubFaculty("IT", universityID);
        UUID diffID = UUID.randomUUID();
        expected.setId(diffID);

        Assert.assertNotNull(savedFaculty.getId());
        Assert.assertNotEquals(expected, savedFaculty);
        Assert.assertNotEquals(expected, receivedFaculty);
    }

    @Test(expected = ValidationException.class)
    public void save_null_throwsException() throws ValidationException {
        facultyDAO.save(null);
    }

    @Test(expected = ValidationException.class)
    public void save_existentFaculty_throwsException() throws ValidationException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("MGMT", universityID);

        facultyDAO.save(facultyToSave);
        facultyDAO.save(facultyToSave);
    }

    private Faculty createStubFaculty(String name, UUID universityID){
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setUniversityID(universityID);
        return faculty;
    }
}
