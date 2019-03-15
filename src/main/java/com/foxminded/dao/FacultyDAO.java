package com.foxminded.dao;

import com.foxminded.Faculty;

import java.util.ArrayList;
import java.util.UUID;

public interface FacultyDAO {

    Faculty save(Faculty faculty);

    Faculty update(Faculty faculty, String newName);

    void delete(UUID id);

    Faculty find(UUID id);

    ArrayList<Faculty> findAllByUniversityID(UUID id);

    Faculty findByIdAndUniversityId(UUID facultyID, UUID universityID);
}
