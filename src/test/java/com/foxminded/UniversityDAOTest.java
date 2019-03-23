package com.foxminded;

import com.foxminded.dao.UniversityDAO;
import com.foxminded.dao.UniversityDAOImpl;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UniversityDAOTest {

    private static UniversityDAO universityDAO;

    @BeforeClass
    public static void initUniversityDAO() {
        universityDAO = new UniversityDAOImpl();
    }

    @Test
    public void save() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        University universityToSave = createStubUniversity("KPI");
        University savedUniversity = universityDAO.save(universityToSave);
        University foundUniversity = universityDAO.findById(savedUniversity.getId());

        University expected = createStubUniversity("KPI");
        expected.setId(savedUniversity.getId());

        assertNotNull(savedUniversity.getId());
        assertEquals(expected, savedUniversity);
        assertEquals(expected, foundUniversity);
    }

    @Test(expected = ValidationException.class)
    public void save_null_throwsException() throws ValidationException, CloneNotSupportedException {
        universityDAO.save(null);
    }

    @Test
    public void save_returnsCopy() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        University beforeSave = createStubUniversity("Oxford");
        University afterSave = universityDAO.save(beforeSave);
        assertNotSame(beforeSave, afterSave);

        University expected = createStubUniversity("Oxford");
        expected.setId(afterSave.getId());

        assertNotNull(afterSave.getId());
        assertEquals(expected, afterSave);
    }

    @Test
    public void update() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        University universityToSave = createStubUniversity("Old name");
        University savedUniversity = universityDAO.save(universityToSave);
        University updatedUniversity = universityDAO.update(savedUniversity, "New name");
        University foundUniversity = universityDAO.findById(updatedUniversity.getId());

        University expected = createStubUniversity("New name");
        expected.setId(savedUniversity.getId());

        assertNotNull(savedUniversity.getId());
        assertEquals(expected, savedUniversity);
        assertEquals(expected, updatedUniversity);
        assertEquals(expected, foundUniversity);
    }

    @Test(expected = EntityNotFoundException.class)
    public void update_notExists_throwsException() throws ValidationException, EntityNotFoundException, CloneNotSupportedException{
        University universityToSave = createStubUniversity("Non Saved, With Id");
        long diffId = Helper.generateNewId();
        universityToSave.setId(diffId);
        universityDAO.update(universityToSave, "New name");
    }

    @Test
    public void update_returnsCopy() throws ValidationException, EntityNotFoundException, CloneNotSupportedException {
        University universityToSave = createStubUniversity("Harvard");
        University beforeUpdate = universityDAO.save(universityToSave);
        University afterUpdate = universityDAO.update(beforeUpdate, "New Harvard");
        assertNotSame(beforeUpdate, afterUpdate);
        assertEquals(beforeUpdate, afterUpdate);
    }

    @Test(expected = EntityNotFoundException.class)
    public void delete() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        University universityToSave = createStubUniversity("Saved");
        University savedUniversity = universityDAO.save(universityToSave);
        University foundUniversity = universityDAO.findById(savedUniversity.getId());
        assertNotNull(foundUniversity);

        universityDAO.delete(savedUniversity.getId());
        universityDAO.findById(savedUniversity.getId());
    }

    @Test(expected = EntityNotFoundException.class)
    public void delete_notExists_throwsException() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        University universityToSave = createStubUniversity("To be deleted twice");
        University savedUniversity = universityDAO.save(universityToSave);
        universityDAO.delete(savedUniversity.getId());
        universityDAO.delete(savedUniversity.getId());
    }

    @Test
    public void findById_returnsCopy() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        University universityToSave = createStubUniversity("Berkley");
        University savedUniversity = universityDAO.save(universityToSave);
        University foundUniversity = universityDAO.findById(savedUniversity.getId());

        assertNotSame(savedUniversity, foundUniversity);
        assertEquals(savedUniversity, foundUniversity);
    }

    @Test(expected = EntityNotFoundException.class)
    public void findById_notExists_throwsException() throws ValidationException, CloneNotSupportedException, EntityNotFoundException {
        University universityToSave = createStubUniversity("Saved and deleted");
        University savedUniversity = universityDAO.save(universityToSave);
        universityDAO.delete(savedUniversity.getId());
        universityDAO.findById(savedUniversity.getId());
    }

    @Test(expected = ValidationException.class)
    public void findById_idIsNull_throwsException() throws ValidationException, EntityNotFoundException, CloneNotSupportedException {
        universityDAO.findById(0);
    }

    @Test
    public void findAll_returnsCopy() throws ValidationException, CloneNotSupportedException {
        University universityToSave1 = createStubUniversity("U1");
        University universityToSave2 = createStubUniversity("U2");
        University universityToSave3 = createStubUniversity("U3");

        University savedUniversity1 = universityDAO.save(universityToSave1);
        University savedUniversity2 = universityDAO.save(universityToSave2);
        University savedUniversity3 = universityDAO.save(universityToSave3);
        List<University> foundUniversities = universityDAO.findAll();

        University expected1 = createStubUniversity("U1");
        expected1.setId(savedUniversity1.getId());
        University expected2 = createStubUniversity("U2");
        expected2.setId(savedUniversity2.getId());
        University expected3 = createStubUniversity("U3");
        expected3.setId(savedUniversity3.getId());

        List<University> expected = new ArrayList<>();
        expected.add(expected1);
        expected.add(expected2);
        expected.add(expected3);
        assertTrue(areListsEqual(expected, foundUniversities));
        assertNotSame(expected, foundUniversities);
    }

    private University createStubUniversity(String name){
        University university = new University();
        university.setName(name);
        return university;
    }

    private boolean areListsEqual(List<University> first, List<University> second) {
        if (first.size() != second.size()) return false;
        int equalCounter = 0;
        for (University university1: first) {
            for (University university2: second){
                if ((university1.getName().equals(university2.getName())) && (university1.getId() == university2.getId()))
                    equalCounter++;
            }
        }
        return equalCounter == first.size();
    }
}
