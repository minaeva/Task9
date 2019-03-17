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
    public void save() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("AM", universityID);

        Faculty savedFaculty = facultyDAO.save(facultyToSave);

        Faculty foundFaculty = facultyDAO.findByID(savedFaculty.getId());

        Faculty expected = createStubFaculty("AM", universityID);
        expected.setId(savedFaculty.getId());

        Assert.assertNotNull(savedFaculty.getId());
        Assert.assertEquals(expected, savedFaculty);
        Assert.assertEquals(expected, foundFaculty);
    }

    @Test
    public void save_idsAreDifferent() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
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
    public void save_null_throwsException() throws ValidationException, CloneNotSupportedException {
        facultyDAO.save(null);
    }

    @Test(expected = ValidationException.class)
    public void save_withId_throwsException() throws ValidationException, CloneNotSupportedException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("LNG", universityID);

        facultyDAO.save(facultyToSave);
        facultyDAO.save(facultyToSave);
    }

    @Test
    public void save_returnsCopy() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("MGMT", universityID);
        Faculty beforeSave = facultyToSave;

        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        Assert.assertNotSame(beforeSave, savedFaculty);

        Faculty foundFaculty = facultyDAO.findByID(savedFaculty.getId());
        Assert.assertNotSame(beforeSave, foundFaculty);

        Faculty expected = createStubFaculty("MGMT", universityID);
        expected.setId(savedFaculty.getId());

        Assert.assertNotNull(savedFaculty.getId());
        Assert.assertEquals(expected, savedFaculty);
        Assert.assertEquals(expected, foundFaculty);
    }

    @Test
    public void update() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("PHT", universityID);

        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        Faculty updatedFaculty = facultyDAO.update(savedFaculty, "PHTECH");
        Faculty foundFaculty = facultyDAO.findByID(updatedFaculty.getId());

        Faculty expected = createStubFaculty("PHTECH", universityID);
        expected.setId(savedFaculty.getId());

        Assert.assertNotNull(savedFaculty.getId());
        Assert.assertEquals(expected, savedFaculty);
        Assert.assertEquals(expected, updatedFaculty);
        Assert.assertEquals(expected, foundFaculty);
    }

    @Test(expected = ValidationException.class)
    public void update_null_throwsException() throws ValidationException, EntityNotFoundException, CloneNotSupportedException {
        facultyDAO.update(null, "Name");
    }

    @Test(expected = EntityNotFoundException.class)
    public void update_withoutId_throwsException() throws ValidationException, EntityNotFoundException, CloneNotSupportedException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("PHT", universityID);

        facultyDAO.update(facultyToSave, "PHTECH");
    }

    @Test(expected = EntityNotFoundException.class)
    public void update_notExists_throwsException() throws ValidationException, EntityNotFoundException, CloneNotSupportedException{
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("RTF", universityID);
        UUID diffID = UUID.randomUUID();
        facultyToSave.setId(diffID);

        facultyDAO.update(facultyToSave, "New name");
    }

    @Test
    public void update_returnsCopy() throws ValidationException, EntityNotFoundException, CloneNotSupportedException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("ITS", universityID);
        Faculty savedFaculty = facultyDAO.save(facultyToSave);

        Faculty beforeUpdate = savedFaculty;

        Faculty afterUpdate = facultyDAO.update(savedFaculty, "NEW ITS");
        Assert.assertNotSame(beforeUpdate, afterUpdate);


        Faculty expected = createStubFaculty("NEW ITS", universityID);
        expected.setId(savedFaculty.getId());

        Assert.assertNotNull(savedFaculty.getId());
        Assert.assertEquals(expected, afterUpdate);
    }

    private Faculty createStubFaculty(String name, UUID universityID){
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setUniversityID(universityID);
        return faculty;
    }

    @Test(expected = EntityNotFoundException.class)
    public void delete() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("TEMP", universityID);

        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        Faculty foundFaculty = facultyDAO.findByID(savedFaculty.getId());
        Assert.assertNotNull(foundFaculty);

        facultyDAO.delete(savedFaculty.getId());
        facultyDAO.findByID(savedFaculty.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void delete_notExists_throwsException() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("TEMP", universityID);

        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        facultyDAO.delete(savedFaculty.getId());
        facultyDAO.delete(savedFaculty.getId());
    }

    @Test(expected = ValidationException.class)
    public void delete_idIsNull_throwsException() throws ValidationException, EntityNotFoundException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("TEMP", universityID);

        facultyDAO.delete(facultyToSave.getId());
    }

    @Test
    public void findById() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave1 = createStubFaculty("PHMF", universityID);
        Faculty facultyToSave2 = createStubFaculty("PHMF", universityID);

        Faculty savedFaculty1 = facultyDAO.save(facultyToSave1);
        Faculty foundFaculty1 = facultyDAO.findByID(savedFaculty1.getId());

        Faculty savedFaculty2 = facultyDAO.save(facultyToSave2);
        Faculty foundFaculty2 = facultyDAO.findByID(savedFaculty2.getId());

        Faculty expected1 = createStubFaculty("PHMF", universityID);
        expected1.setId(savedFaculty1.getId());
        Faculty expected2 = createStubFaculty("PHMF", universityID);
        expected2.setId(savedFaculty2.getId());

        Assert.assertNotNull(savedFaculty1.getId());
        Assert.assertNotNull(savedFaculty2.getId());
        Assert.assertNotEquals(savedFaculty1, savedFaculty2);
        Assert.assertNotEquals(foundFaculty1, foundFaculty2);
        Assert.assertEquals(expected1, foundFaculty1);
        Assert.assertEquals(expected2, foundFaculty2);
    }

    @Test
    public void findById_returnsCopy() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("SMM", universityID);

        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        Faculty foundFaculty = facultyDAO.findByID(savedFaculty.getId());

        Assert.assertNotSame(savedFaculty, foundFaculty);

        Faculty expected = createStubFaculty("SMM", universityID);
        expected.setId(savedFaculty.getId());

        Assert.assertNotNull(savedFaculty.getId());
        Assert.assertEquals(expected, savedFaculty);
        Assert.assertEquals(expected, foundFaculty);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findById_notExists_throwsException() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("TEMP", universityID);

        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        facultyDAO.delete(savedFaculty.getId());

        facultyDAO.findByID(savedFaculty.getId());
    }

    @Test(expected = ValidationException.class)
    public void findById_idIsNull_throwsException() throws ValidationException, EntityNotFoundException, CloneNotSupportedException {
        facultyDAO.findByID(null);
    }

    @Test
    public void findByIdAndUniversityId() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("FMM", universityID);

        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        Faculty foundFaculty = facultyDAO.findByIdAndUniversityId(savedFaculty.getId(), universityID);

        Faculty expected = createStubFaculty("FMM", universityID);
        expected.setId(savedFaculty.getId());

        Assert.assertNotNull(savedFaculty.getId());
        Assert.assertEquals(expected, foundFaculty);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdAndUniversityId_universityIdNotExists_throwsException() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("PHTF", universityID);

        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        UUID otherID = UUID.randomUUID();
        facultyDAO.findByIdAndUniversityId(savedFaculty.getId(), otherID);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdAndUniversityId_facultyIdNotExists_throwsException() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("RTF", universityID);

        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        facultyDAO.delete(savedFaculty.getId());

        facultyDAO.findByIdAndUniversityId(savedFaculty.getId(), universityID);
    }

    @Test(expected = ValidationException.class)
    public void findByIdAndUniversityId_universityIdIsNull_throwsException() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        facultyDAO.findByIdAndUniversityId(null, UUID.randomUUID());
    }

    @Test(expected = ValidationException.class)
    public void findByIdAndUniversityId_facultyIdIsNull_throwsException() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        facultyDAO.findByIdAndUniversityId(UUID.randomUUID(), null);
    }

}

/*



findByIdAndUniversityId_returnsCopy
findByUniversityId
findByUniversityId_universityIdNotExists_returnsEmptyList
findByUniversityId_returnsCopy
findByUniversityId_idIsNull_throwsException
 */