package com.foxminded;

import com.foxminded.dao.FacultyDAO;
import com.foxminded.dao.FacultyDAOImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.ValidationException;
import java.util.UUID;

public class FacultyDAOTest {

    private FacultyDAO facultyDAO;

    @Before
    public void initFacultyDAO(){
        facultyDAO = new FacultyDAOImpl();
    }

    @Test
    public void save(){// throws ValidationException, EntityNotFoundException {
        University university = new University("KPI");
        UUID universityID = university.getId();
        System.out.println(universityID);

        Faculty facultyToSave = createStubFaculty("AM", universityID);

        Faculty savedFaculty = facultyDAO.save(facultyToSave);

        Faculty receivedFaculty = facultyDAO.find(savedFaculty.getId());

        Assert.assertEquals(savedFaculty, receivedFaculty);

        Faculty expected = createStubFaculty("AM", universityID);
        expected.setId(savedFaculty.getId());

        Assert.assertNotNull(savedFaculty.getId());
   //     Assert.assertEquals(expected, savedFaculty);
    //    Assert.assertEquals(expected, receivedFaculty);
    }

    private Faculty createStubFaculty(String name, UUID id){
        Faculty faculty = new Faculty(name);
        faculty.setUniverityID(id);
        return faculty;
    }
}
