package com.foxminded;

import static org.junit.Assert.*;
import com.foxminded.dao.FacultyDAO;
import com.foxminded.dao.FacultyDAOImpl;
import com.foxminded.dao.UniversityDAO;
import com.foxminded.dao.UniversityDAOImpl;
import org.junit.Test;

public class UniversityTest {

    private static UniversityDAO universityDAO = new UniversityDAOImpl();
//    private static FacultyDAO facultyDAO = new FacultyDAOImpl();

    @Test
    public void createFaculty() throws ValidationException, CloneNotSupportedException{
    University university = new University("Oxford");
    University savedUniversity = universityDAO.save(university);
    Faculty createdFaculty = savedUniversity.createFaculty("IT");

    Faculty expected = createStubFaculty("IT", savedUniversity.getId());
    expected.setId(createdFaculty.getId());
    assertEquals(expected, createdFaculty);
    }

    @Test
    public void updateFaculty() throws ValidationException, CloneNotSupportedException, EntityNotFoundException{
        University university = new University("Cambridge");
        University savedUniversity = universityDAO.save(university);
        Faculty createdFaculty = savedUniversity.createFaculty("MGMT");
        Faculty updatedFaculty = savedUniversity.updateFaculty(createdFaculty.getId(), "NEW");

        Faculty expected = createStubFaculty("NEW", savedUniversity.getId());
        expected.setId(createdFaculty.getId());
        assertEquals(expected, updatedFaculty);
    }

    @Test(expected = EntityNotFoundException.class)
    public void dismantleFaculty() throws ValidationException, CloneNotSupportedException, EntityNotFoundException{
        University university = new University("KPI");
        University savedUniversity = universityDAO.save(university);
        Faculty createdFaculty = savedUniversity.createFaculty("IASA");
        savedUniversity.dismantleFaculty(createdFaculty.getId());
        savedUniversity.findFaculty(createdFaculty.getId());
    }

    @Test
    public void findFaculty() throws ValidationException, CloneNotSupportedException, EntityNotFoundException{
        University university = new University("Berkley");
        University savedUniversity = universityDAO.save(university);
        Faculty createdFaculty = savedUniversity.createFaculty("CMP");
        Faculty foundFaculty = savedUniversity.findFaculty(createdFaculty.getId());

        Faculty expected = createStubFaculty("CMP", savedUniversity.getId());
        expected.setId(createdFaculty.getId());
        assertEquals(expected, foundFaculty);
    }

    private long newId(){
        return IdGenerator.newId();
    }

    private Faculty createStubFaculty(String name, long universityId){
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setUniversityId(universityId);
        return faculty;
    }
}
