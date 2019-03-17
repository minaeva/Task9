package com.foxminded.dao;

import com.foxminded.EntityNotFoundException;
import com.foxminded.ValidationException;
import com.foxminded.Faculty;
import java.util.*;

public class FacultyDAOImpl implements FacultyDAO{

    static private Map<UUID, Faculty> faculties = new HashMap<>();

    public Faculty save(Faculty faculty) throws ValidationException, CloneNotSupportedException{
        if (faculty == null) throw new ValidationException("Null faculty");
        if (faculty.getId() != null) throw new ValidationException("Faculty with ID " + faculty.getId() + " already exists");
        for (UUID id: faculties.keySet()){
            if (id == faculty.getId()) throw new ValidationException("Faculty with ID " + faculty.getId() + " already exists");
        }

        UUID id = UUID.randomUUID();
        faculty.setId(id);
        faculties.put(id, faculty);
        return faculty.clone();
    }

    public Faculty update(Faculty faculty, String newName) throws ValidationException, EntityNotFoundException, CloneNotSupportedException {
        if (faculty == null) throw new ValidationException("Null faculty");
        if (faculty.getId() == null) throw new EntityNotFoundException("Null ID => Faculty doesn't exist");
        if (!faculties.containsValue(faculty)) throw new EntityNotFoundException("Faculty " + faculty.getName() + " doesn't exist");
        faculty.setName(newName);
        faculties.put(faculty.getId(), faculty);
        return faculty.clone();
    }

    public void delete(UUID id) throws ValidationException, EntityNotFoundException {
        if (id == null) throw new ValidationException("Null ID");
        if (!faculties.containsKey(id)) throw new EntityNotFoundException("Faculty with id " + id + " doesn't exist");
        faculties.remove(id);
    }

    public Faculty findByID(UUID id) throws ValidationException, EntityNotFoundException, CloneNotSupportedException{
        if (id == null) throw new ValidationException("Null ID");
        if (!faculties.containsKey(id)) throw new EntityNotFoundException("Faculty with id " + id + " doesn't exist");
        return faculties.get(id).clone();
    }

    public Faculty findByIdAndUniversityId(UUID facultyID, UUID universityID) throws ValidationException, EntityNotFoundException, CloneNotSupportedException{
        if ((facultyID == null)||(universityID == null)) throw new ValidationException("Null ID");
        if (!faculties.containsKey(facultyID)) throw new EntityNotFoundException("Faculty with id " + facultyID + " doesn't exist");
        if (!(faculties.get(facultyID).getUniversityID() == universityID)) throw new EntityNotFoundException("Faculty with university ID " + universityID + " doesn't exist");

        Faculty foundFaculty = faculties.get(facultyID);
        return foundFaculty.clone();
    }

    public List<Faculty> findByUniversityID(UUID id) throws CloneNotSupportedException, ValidationException{
        if (id == null) throw new ValidationException("Null ID");
        List<Faculty> allUniversityFaculties = new ArrayList<>(faculties.values());
        List<Faculty> clonedFaculties = new ArrayList<>();
        for (Faculty faculty: allUniversityFaculties) {
            clonedFaculties.add(faculty.clone());
        }
        return clonedFaculties;
    }
}


