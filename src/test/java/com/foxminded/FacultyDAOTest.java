package com.foxminded;

import com.foxminded.dao.FacultyDAO;
import com.foxminded.dao.FacultyDAOImpl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
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
        Faculty beforeSave = createStubFaculty("MGMT", universityID);

        Faculty afterSave = facultyDAO.save(beforeSave);
        Assert.assertNotSame(beforeSave, afterSave);

        Faculty foundFaculty = facultyDAO.findByID(afterSave.getId());
        Assert.assertNotSame(beforeSave, foundFaculty);

        Faculty expected = createStubFaculty("MGMT", universityID);
        expected.setId(afterSave.getId());

        Assert.assertNotNull(afterSave.getId());
        Assert.assertEquals(expected, afterSave);
        Assert.assertEquals(expected, foundFaculty);
    }

    @Test
    public void update() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("Old name", universityID);

        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        Faculty updatedFaculty = facultyDAO.update(savedFaculty, "New name");
        Faculty foundFaculty = facultyDAO.findByID(updatedFaculty.getId());

        Faculty expected = createStubFaculty("New name", universityID);
        expected.setId(savedFaculty.getId());

        Assert.assertNotNull(savedFaculty.getId());
        Assert.assertEquals(expected, savedFaculty);
        Assert.assertEquals(expected, updatedFaculty);
        Assert.assertEquals(expected, foundFaculty);
    }

    @Test(expected = ValidationException.class)
    public void update_null_throwsException() throws ValidationException, EntityNotFoundException, CloneNotSupportedException {
        facultyDAO.update(null, "New name");
    }

    @Test(expected = EntityNotFoundException.class)
    public void update_withoutId_throwsException() throws ValidationException, EntityNotFoundException, CloneNotSupportedException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("Non Saved", universityID);

        facultyDAO.update(facultyToSave, "New name");
    }

    @Test(expected = EntityNotFoundException.class)
    public void update_notExists_throwsException() throws ValidationException, EntityNotFoundException, CloneNotSupportedException{
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("Non Saved, With ID", universityID);
        UUID diffID = UUID.randomUUID();
        facultyToSave.setId(diffID);

        facultyDAO.update(facultyToSave, "New name");
    }

    @Test
    public void update_returnsCopy() throws ValidationException, EntityNotFoundException, CloneNotSupportedException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("ITS", universityID);
        Faculty beforeUpdate = facultyDAO.save(facultyToSave);

        Faculty afterUpdate = facultyDAO.update(beforeUpdate, "NEW ITS");
        Assert.assertNotSame(beforeUpdate, afterUpdate);


        Faculty expected = createStubFaculty("NEW ITS", universityID);
        expected.setId(beforeUpdate.getId());

        Assert.assertNotNull(beforeUpdate.getId());
        Assert.assertEquals(expected, afterUpdate);
    }

    @Test(expected = EntityNotFoundException.class)
    public void delete() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("Saved", universityID);

        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        Faculty foundFaculty = facultyDAO.findByID(savedFaculty.getId());
        Assert.assertNotNull(foundFaculty);

        facultyDAO.delete(savedFaculty.getId());
        facultyDAO.findByID(savedFaculty.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void delete_notExists_throwsException() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("To be deleted twice", universityID);

        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        facultyDAO.delete(savedFaculty.getId());
        facultyDAO.delete(savedFaculty.getId());
    }

    @Test(expected = ValidationException.class)
    public void delete_idIsNull_throwsException() throws ValidationException, EntityNotFoundException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("Non saved", universityID);

        facultyDAO.delete(facultyToSave.getId());
    }

    @Test
    public void findById() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave1 = createStubFaculty("Same name, diff objects", universityID);
        Faculty facultyToSave2 = createStubFaculty("Same name, diff objects", universityID);

        Faculty savedFaculty1 = facultyDAO.save(facultyToSave1);
        Faculty foundFaculty1 = facultyDAO.findByID(savedFaculty1.getId());

        Faculty savedFaculty2 = facultyDAO.save(facultyToSave2);
        Faculty foundFaculty2 = facultyDAO.findByID(savedFaculty2.getId());

        Faculty expected1 = createStubFaculty("Same name, diff objects", universityID);
        expected1.setId(savedFaculty1.getId());
        Faculty expected2 = createStubFaculty("Same name, diff objects", universityID);
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
        Faculty facultyToSave = createStubFaculty("Saved and deleted", universityID);

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
        Faculty facultyToSave = createStubFaculty("Saved faculty, other university", universityID);

        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        UUID otherID = UUID.randomUUID();
        facultyDAO.findByIdAndUniversityId(savedFaculty.getId(), otherID);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdAndUniversityId_facultyIdNotExists_throwsException() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("Deleted", universityID);

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

    @Test
    public void findByIdAndUniversityId_returnsCopy() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("FEL", universityID);

        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        Faculty foundFaculty = facultyDAO.findByIdAndUniversityId(savedFaculty.getId(), universityID);

        Assert.assertNotSame(savedFaculty, foundFaculty);
        Assert.assertEquals(savedFaculty, foundFaculty);

        Faculty expected = createStubFaculty("FEL", universityID);
        expected.setId(savedFaculty.getId());

        Assert.assertNotNull(savedFaculty.getId());
        Assert.assertEquals(expected, savedFaculty);
        Assert.assertEquals(expected, foundFaculty);
    }

    @Test
    public void findByUniversityId() throws ValidationException, CloneNotSupportedException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave1 = createStubFaculty("F1", universityID);
        Faculty facultyToSave2 = createStubFaculty("F2", universityID);
        Faculty facultyToSave3 = createStubFaculty("F3", universityID);

        Faculty savedFaculty1 = facultyDAO.save(facultyToSave1);
        Faculty savedFaculty2 = facultyDAO.save(facultyToSave2);
        Faculty savedFaculty3 = facultyDAO.save(facultyToSave3);
        List<Faculty> foundFaculties = facultyDAO.findByUniversityID(universityID);

        Faculty expected1 = createStubFaculty("F1", universityID);
        expected1.setId(savedFaculty1.getId());
        Faculty expected2 = createStubFaculty("F2", universityID);
        expected2.setId(savedFaculty2.getId());
        Faculty expected3 = createStubFaculty("F3", universityID);
        expected3.setId(savedFaculty3.getId());

        List<Faculty> expected = new ArrayList<>();
        expected.add(expected1);
        expected.add(expected2);
        expected.add(expected3);
        Assert.assertTrue(areListsEqual(expected, foundFaculties));
    }

    @Test
    public void findByUniversityId_universityIdNotExists_returnsEmptyList() throws ValidationException, CloneNotSupportedException {
        UUID universityID = UUID.randomUUID();
        Faculty facultyToSave = createStubFaculty("FTF", universityID);

        facultyDAO.save(facultyToSave);
        UUID otherID = UUID.randomUUID();
        List<Faculty> foundFaculties = facultyDAO.findByUniversityID(otherID);
        Assert.assertNull(foundFaculties);
    }

    @Test
    public void findByUniversityId_returnsCopy() throws ValidationException, CloneNotSupportedException {
        UUID universityID = UUID.randomUUID();

         Faculty facultyToSave1 = createStubFaculty("F1", universityID);
         Faculty facultyToSave2 = createStubFaculty("F2", universityID);
         Faculty facultyToSave3 = createStubFaculty("F3", universityID);

         Faculty savedFaculty1 = facultyDAO.save(facultyToSave1);
         Faculty savedFaculty2 = facultyDAO.save(facultyToSave2);
         Faculty savedFaculty3 = facultyDAO.save(facultyToSave3);

         List<Faculty> savedFaculties = new ArrayList<>();
         savedFaculties.add(savedFaculty1);
         savedFaculties.add(savedFaculty2);
         savedFaculties.add(savedFaculty3);

         List<Faculty> foundFaculties = facultyDAO.findByUniversityID(universityID);
        Assert.assertNotSame(savedFaculties, foundFaculties);
    }

    @Test(expected = ValidationException.class)
    public void findByUniversityId_idIsNull_throwsException() throws ValidationException {
        facultyDAO.findByUniversityID(null);
    }

    private boolean areListsEqual(List<Faculty> first, List<Faculty> second) {
        if (first.size() != second.size()) return false;
        int equalCounter = 0;
        for (Faculty faculty1: first) {
            for (Faculty faculty2: second){
                if ((faculty1.getName().equals(faculty2.getName())) && (faculty1.getId() == faculty2.getId()))
                    equalCounter++;
            }
        }
        return equalCounter == first.size();
    }

    private Faculty createStubFaculty(String name, UUID universityID){
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setUniversityID(universityID);
        return faculty;
    }


}