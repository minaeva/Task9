package com.foxminded.dao;

import com.foxminded.EntityNotFoundException;
import com.foxminded.ValidationException;
import com.foxminded.Faculty;
import com.foxminded.IdGenerator;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FacultyDAOImpl implements FacultyDAO{

    static private Map<Long, Faculty> faculties = new HashMap<>();

    public Faculty save(Faculty faculty) throws ValidationException, CloneNotSupportedException{
        if (faculty == null) throw new ValidationException("Null faculty");
        if (faculty.getId() != 0) throw new ValidationException("Faculty with Id " + faculty.getId() + " already exists");
        for (long id: faculties.keySet()){
            if (id == faculty.getId()) throw new ValidationException("Faculty with Id " + faculty.getId() + " already exists");
        }
        long id = IdGenerator.newId();
        faculty.setId(id);
        faculties.put(id, faculty);
        return faculty.clone();
    }

    public Faculty update(Faculty faculty, String newName) throws ValidationException, EntityNotFoundException, CloneNotSupportedException {
        if (faculty == null) throw new ValidationException("Null faculty");
        if (faculty.getId() == 0) throw new EntityNotFoundException("Null is not set => Faculty doesn't exist");
        if (!faculties.containsValue(faculty)) throw new EntityNotFoundException("Faculty " + faculty.getName() + " doesn't exist");
        faculty.setName(newName);
        faculties.put(faculty.getId(), faculty);
        return faculty.clone();
    }

    public void delete(long id) throws ValidationException, EntityNotFoundException {
        if (id == 0) throw new ValidationException("Id is not set");
        if (!faculties.containsKey(id)) throw new EntityNotFoundException("Faculty with id " + id + " doesn't exist");
        faculties.remove(id);
    }

    public Faculty findById(long id) throws ValidationException, EntityNotFoundException, CloneNotSupportedException{
        if (id == 0) throw new ValidationException("Id is not");
        if (!faculties.containsKey(id)) throw new EntityNotFoundException("Faculty with id " + id + " doesn't exist");
        return faculties.get(id).clone();
    }

    public Faculty findByIdAndUniversityId(long facultyId, long universityId) throws ValidationException, EntityNotFoundException, CloneNotSupportedException{
        if ((facultyId == 0)||(universityId == 0)) throw new ValidationException("Id is not set");
        if (!faculties.containsKey(facultyId)) throw new EntityNotFoundException("Faculty with id " + facultyId + " doesn't exist");
        if (!(faculties.get(facultyId).getUniversityId() == universityId)) throw new EntityNotFoundException("Faculty with university Id " + universityId + " doesn't exist");
        Faculty foundFaculty = faculties.get(facultyId);
        return foundFaculty.clone();
    }

    public List<Faculty> findByUniversityId(long id) throws ValidationException{
        if (id == 0) throw new ValidationException("Id is not set");
        List<Faculty> allUniversityFaculties = new ArrayList<>(faculties.values());

        Predicate<Faculty> p = f -> f.getUniversityId() == id;
        if (!allUniversityFaculties.stream().anyMatch(p)) return null;

        List<Faculty> foundFaculties = allUniversityFaculties.stream().filter(p).collect(Collectors.toList());
        return new ArrayList(foundFaculties);
    }
}


