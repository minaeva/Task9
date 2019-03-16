package com.foxminded.dao;

import com.foxminded.EntityNotFoundException;
import com.foxminded.ValidationException;
import com.foxminded.Faculty;
import java.util.*;

public class FacultyDAOImpl implements FacultyDAO{

    static private Map<UUID, Faculty> faculties = new HashMap<>();

    public Faculty save(Faculty faculty) throws ValidationException{
        if (faculty == null) throw new ValidationException("Null faculty");
        for (Faculty f: faculties.values()) {
            if (f.getName().equals(faculty.getName())) throw new ValidationException("Faculty " + faculty.getName() + " already exists");
        }
        UUID id = UUID.randomUUID();
        faculty.setId(id);
        faculties.put(id, faculty);
        return faculty;
    }

    public Faculty update(Faculty faculty, String newName) throws ValidationException, EntityNotFoundException {
        if (faculty == null) throw new ValidationException("Null faculty");
        if (!faculties.containsValue(faculty)) throw new EntityNotFoundException("Faculty " + faculty.getName() + " doesn't exist");
        faculty.setName(newName);
        return faculty;
    }

    public void delete(UUID id) throws ValidationException, EntityNotFoundException {
        if (id == null) throw new ValidationException("Null ID");
        if (!faculties.containsKey(id)) throw new EntityNotFoundException("Faculty with id " + id + " doesn't exist");
        faculties.remove(id);
    }

    public Faculty findByID(UUID id) throws CloneNotSupportedException{
        return (Faculty) faculties.get(id).clone();
    }

    public List<Faculty> findAllByUniversityID(UUID id) throws CloneNotSupportedException{
        List<Faculty> allUniversityFaculties = new ArrayList<>(faculties.values());
        List<Faculty> clonedFaculties = new ArrayList<>();
        for (Faculty faculty: allUniversityFaculties
             ) {
            clonedFaculties.add((Faculty) faculty.clone());
        }
        return clonedFaculties;
    }

    public Faculty findByIdAndUniversityId(UUID facultyID, UUID universityID) throws CloneNotSupportedException{
        if (faculties.get(facultyID).getUniversityID() == universityID) {
            Faculty foundFaculty = faculties.get(facultyID);
            return (Faculty) foundFaculty.clone();
        }
        System.out.println("Cannot find faculty with id " + facultyID + " and university " + universityID);
        return null;
    }

}


