package com.foxminded;

import com.foxminded.dao.FacultyDAO;
import com.foxminded.dao.FacultyDAOImpl;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class FacultyDAOTest {

    private static FacultyDAO facultyDAO;

    @BeforeClass
    public static void initFacultyDAO(){
        facultyDAO = new FacultyDAOImpl();
    }

    @Test
    public void save() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        long universityId = IdGenerator.newId();
        Faculty facultyToSave = createStubFaculty("AM", universityId);
        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        Faculty foundFaculty = facultyDAO.findById(savedFaculty.getId());

        Faculty expected = createStubFaculty("AM", universityId);
        expected.setId(savedFaculty.getId());

        assertNotNull(savedFaculty.getId());
        assertEquals(expected, savedFaculty);
        assertEquals(expected, foundFaculty);
    }

    @Test
    public void save_idsAreDifferent() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        long universityId = IdGenerator.newId();
        Faculty facultyToSave = createStubFaculty("IT", universityId);
        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        Faculty receivedFaculty = facultyDAO.findById(savedFaculty.getId());

        Faculty expected = createStubFaculty("IT", universityId);
        long diffId = IdGenerator.newId();
        expected.setId(diffId);

        assertNotNull(savedFaculty.getId());
        assertNotEquals(expected, savedFaculty);
        assertNotEquals(expected, receivedFaculty);
    }

    @Test(expected = ValidationException.class)
    public void save_null_throwsException() throws ValidationException, CloneNotSupportedException {
        facultyDAO.save(null);
    }

    @Test(expected = ValidationException.class)
    public void save_withId_throwsException() throws ValidationException, CloneNotSupportedException {
        long universityId = IdGenerator.newId();
        Faculty facultyToSave = createStubFaculty("LNG", universityId);
        facultyDAO.save(facultyToSave);
        facultyDAO.save(facultyToSave);
    }

    @Test
    public void save_returnsCopy() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        long universityId = IdGenerator.newId();
        Faculty beforeSave = createStubFaculty("MGMT", universityId);
        Faculty afterSave = facultyDAO.save(beforeSave);
        assertNotSame(beforeSave, afterSave);

        Faculty foundFaculty = facultyDAO.findById(afterSave.getId());
        assertNotSame(beforeSave, foundFaculty);

        Faculty expected = createStubFaculty("MGMT", universityId);
        expected.setId(afterSave.getId());

        assertNotNull(afterSave.getId());
        assertEquals(expected, afterSave);
        assertEquals(expected, foundFaculty);
    }

    @Test
    public void update() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        long universityId = IdGenerator.newId();
        Faculty facultyToSave = createStubFaculty("Old name", universityId);

        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        Faculty updatedFaculty = facultyDAO.update(savedFaculty, "New name");
        Faculty foundFaculty = facultyDAO.findById(updatedFaculty.getId());

        Faculty expected = createStubFaculty("New name", universityId);
        expected.setId(savedFaculty.getId());

        assertNotNull(savedFaculty.getId());
        assertEquals(expected, savedFaculty);
        assertEquals(expected, updatedFaculty);
        assertEquals(expected, foundFaculty);
    }

    @Test(expected = ValidationException.class)
    public void update_null_throwsException() throws ValidationException, EntityNotFoundException, CloneNotSupportedException {
        facultyDAO.update(null, "New name");
    }

    @Test(expected = EntityNotFoundException.class)
    public void update_withoutId_throwsException() throws ValidationException, EntityNotFoundException, CloneNotSupportedException {
        long universityId = IdGenerator.newId();
        Faculty facultyToSave = createStubFaculty("Non Saved", universityId);
        facultyDAO.update(facultyToSave, "New name");
    }

    @Test(expected = EntityNotFoundException.class)
    public void update_notExists_throwsException() throws ValidationException, EntityNotFoundException, CloneNotSupportedException{
        long universityId = IdGenerator.newId();
        Faculty facultyToSave = createStubFaculty("Non Saved, With Id", universityId);
        long diffId = IdGenerator.newId();
        facultyToSave.setId(diffId);
        facultyDAO.update(facultyToSave, "New name");
    }

    @Test
    public void update_returnsCopy() throws ValidationException, EntityNotFoundException, CloneNotSupportedException {
        long universityId = IdGenerator.newId();
        Faculty facultyToSave = createStubFaculty("ITS", universityId);
        Faculty beforeUpdate = facultyDAO.save(facultyToSave);
        Faculty afterUpdate = facultyDAO.update(beforeUpdate, "NEW ITS");
        assertNotSame(beforeUpdate, afterUpdate);
        Faculty expected = createStubFaculty("NEW ITS", universityId);
        expected.setId(beforeUpdate.getId());

        assertNotNull(beforeUpdate.getId());
        assertEquals(expected, afterUpdate);
    }

    @Test(expected = EntityNotFoundException.class)
    public void delete() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        long universityId = IdGenerator.newId();
        Faculty facultyToSave = createStubFaculty("Saved", universityId);
        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        Faculty foundFaculty = facultyDAO.findById(savedFaculty.getId());
        assertNotNull(foundFaculty);

        facultyDAO.delete(savedFaculty.getId());
        facultyDAO.findById(savedFaculty.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void delete_notExists_throwsException() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        long universityId = IdGenerator.newId();
        Faculty facultyToSave = createStubFaculty("To be deleted twice", universityId);
        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        facultyDAO.delete(savedFaculty.getId());
        facultyDAO.delete(savedFaculty.getId());
    }

    @Test(expected = ValidationException.class)
    public void delete_idIsNull_throwsException() throws ValidationException, EntityNotFoundException {
        long universityId = IdGenerator.newId();
        Faculty facultyToSave = createStubFaculty("Non saved", universityId);
        facultyDAO.delete(facultyToSave.getId());
    }

    @Test
    public void findById() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        long universityId = IdGenerator.newId();
        Faculty facultyToSave1 = createStubFaculty("Same name, diff objects", universityId);
        Faculty facultyToSave2 = createStubFaculty("Same name, diff objects", universityId);

        Faculty savedFaculty1 = facultyDAO.save(facultyToSave1);
        Faculty foundFaculty1 = facultyDAO.findById(savedFaculty1.getId());
        Faculty savedFaculty2 = facultyDAO.save(facultyToSave2);
        Faculty foundFaculty2 = facultyDAO.findById(savedFaculty2.getId());

        Faculty expected1 = createStubFaculty("Same name, diff objects", universityId);
        expected1.setId(savedFaculty1.getId());
        Faculty expected2 = createStubFaculty("Same name, diff objects", universityId);
        expected2.setId(savedFaculty2.getId());

        assertNotNull(savedFaculty1.getId());
        assertNotNull(savedFaculty2.getId());
        assertNotEquals(savedFaculty1, savedFaculty2);
        assertNotEquals(foundFaculty1, foundFaculty2);
        assertEquals(expected1, foundFaculty1);
        assertEquals(expected2, foundFaculty2);
    }

    @Test
    public void findById_returnsCopy() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        long universityId = IdGenerator.newId();
        Faculty facultyToSave = createStubFaculty("SMM", universityId);

        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        Faculty foundFaculty = facultyDAO.findById(savedFaculty.getId());
        assertNotSame(savedFaculty, foundFaculty);

        Faculty expected = createStubFaculty("SMM", universityId);
        expected.setId(savedFaculty.getId());

        assertNotNull(savedFaculty.getId());
        assertEquals(expected, savedFaculty);
        assertEquals(expected, foundFaculty);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findById_notExists_throwsException() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        long universityId = IdGenerator.newId();
        Faculty facultyToSave = createStubFaculty("Saved and deleted", universityId);
        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        facultyDAO.delete(savedFaculty.getId());
        facultyDAO.findById(savedFaculty.getId());
    }

    @Test(expected = ValidationException.class)
    public void findById_idIsZero_throwsException() throws ValidationException, EntityNotFoundException, CloneNotSupportedException {
        facultyDAO.findById(0);
    }

    @Test
    public void findByIdAndUniversityId() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        long universityId = IdGenerator.newId();
        Faculty facultyToSave = createStubFaculty("FMM", universityId);
        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        Faculty foundFaculty = facultyDAO.findByIdAndUniversityId(savedFaculty.getId(), universityId);

        Faculty expected = createStubFaculty("FMM", universityId);
        expected.setId(savedFaculty.getId());

        assertNotNull(savedFaculty.getId());
        assertEquals(expected, foundFaculty);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdAndUniversityId_universityIdNotExists_throwsException() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        long universityId = IdGenerator.newId();
        Faculty facultyToSave = createStubFaculty("Saved faculty, other university", universityId);

        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        long otherId = IdGenerator.newId();
        facultyDAO.findByIdAndUniversityId(savedFaculty.getId(), otherId);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findByIdAndUniversityId_facultyIdNotExists_throwsException() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        long universityId = IdGenerator.newId();
        Faculty facultyToSave = createStubFaculty("Deleted", universityId);
        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        facultyDAO.delete(savedFaculty.getId());
        facultyDAO.findByIdAndUniversityId(savedFaculty.getId(), universityId);
    }

    @Test(expected = ValidationException.class)
    public void findByIdAndUniversityId_universityIdIsNull_throwsException() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        long universityId = IdGenerator.newId();
        facultyDAO.findByIdAndUniversityId(0, universityId);
    }

    @Test(expected = ValidationException.class)
    public void findByIdAndUniversityId_facultyIdIsZero_throwsException() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        long facultyId = IdGenerator.newId();
        facultyDAO.findByIdAndUniversityId(facultyId, 0);
    }

    @Test
    public void findByIdAndUniversityId_returnsCopy() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        long universityId = IdGenerator.newId();
        Faculty facultyToSave = createStubFaculty("FEL", universityId);
        Faculty savedFaculty = facultyDAO.save(facultyToSave);
        Faculty foundFaculty = facultyDAO.findByIdAndUniversityId(savedFaculty.getId(), universityId);

        assertNotSame(savedFaculty, foundFaculty);
        assertEquals(savedFaculty, foundFaculty);

        Faculty expected = createStubFaculty("FEL", universityId);
        expected.setId(savedFaculty.getId());

        assertNotNull(savedFaculty.getId());
        assertEquals(expected, savedFaculty);
        assertEquals(expected, foundFaculty);
    }

    @Test
    public void findByUniversityId() throws ValidationException, CloneNotSupportedException {
        long universityId = IdGenerator.newId();
        Faculty facultyToSave1 = createStubFaculty("F1", universityId);
        Faculty facultyToSave2 = createStubFaculty("F2", universityId);
        Faculty facultyToSave3 = createStubFaculty("F3", universityId);

        Faculty savedFaculty1 = facultyDAO.save(facultyToSave1);
        Faculty savedFaculty2 = facultyDAO.save(facultyToSave2);
        Faculty savedFaculty3 = facultyDAO.save(facultyToSave3);
        List<Faculty> foundFaculties = facultyDAO.findByUniversityId(universityId);

        Faculty expected1 = createStubFaculty("F1", universityId);
        expected1.setId(savedFaculty1.getId());
        Faculty expected2 = createStubFaculty("F2", universityId);
        expected2.setId(savedFaculty2.getId());
        Faculty expected3 = createStubFaculty("F3", universityId);
        expected3.setId(savedFaculty3.getId());

        List<Faculty> expected = new ArrayList<>();
        expected.add(expected1);
        expected.add(expected2);
        expected.add(expected3);
        assertTrue(areListsEqual(expected, foundFaculties));
    }

    @Test
    public void findByUniversityId_universityIdNotExists_returnsEmptyList() throws ValidationException, CloneNotSupportedException {
        long universityId = IdGenerator.newId();
        Faculty facultyToSave = createStubFaculty("FTF", universityId);
        facultyDAO.save(facultyToSave);
        long otherId = IdGenerator.newId();
        List<Faculty> foundFaculties = facultyDAO.findByUniversityId(otherId);
        assertNull(foundFaculties);
    }

    @Test
    public void findByUniversityId_returnsCopy() throws ValidationException, CloneNotSupportedException {
        long universityId = IdGenerator.newId();
         Faculty facultyToSave1 = createStubFaculty("F1", universityId);
         Faculty facultyToSave2 = createStubFaculty("F2", universityId);
         Faculty facultyToSave3 = createStubFaculty("F3", universityId);

         Faculty savedFaculty1 = facultyDAO.save(facultyToSave1);
         Faculty savedFaculty2 = facultyDAO.save(facultyToSave2);
         Faculty savedFaculty3 = facultyDAO.save(facultyToSave3);

         List<Faculty> savedFaculties = new ArrayList<>();
         savedFaculties.add(savedFaculty1);
         savedFaculties.add(savedFaculty2);
         savedFaculties.add(savedFaculty3);

         List<Faculty> foundFaculties = facultyDAO.findByUniversityId(universityId);
         assertNotSame(savedFaculties, foundFaculties);
    }

    @Test(expected = ValidationException.class)
    public void findByUniversityId_idIsZero_throwsException() throws ValidationException {
        facultyDAO.findByUniversityId(0);
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

    private Faculty createStubFaculty(String name, long universityId){
        Faculty faculty = new Faculty(name);
        faculty.setUniversityId(universityId);
        return faculty;
    }
}