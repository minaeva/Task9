package com.foxminded.dao;

import com.foxminded.Faculty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FacultyDAOImpl implements FacultyDAO {

    static private Map<UUID, Faculty> faculties = new HashMap<>();

    public Faculty save(Faculty faculty) {
        UUID id = faculty.getId();
        faculties.put(id, faculty);
        return faculty;
    }

    public Faculty update(Faculty faculty, String newName) {
        faculty.setName(newName);
        return faculty;
    }

    public void delete(UUID id) {
        faculties.remove(id);
    }

    public Faculty find(UUID id) {
        return faculties.get(id);
    }

    public ArrayList<Faculty> findAllByUniversityID(UUID id) {
        return new ArrayList<>(faculties.values());
    }

    public Faculty findByIdAndUniversityId(UUID facultyID, UUID universityID){
        if (faculties.get(facultyID).getUniverityID() == universityID) {
            return faculties.get(facultyID);
        }
        System.out.println("Cannot find faculty with id " + facultyID + " and university " + universityID);
        return null;
    }

}


