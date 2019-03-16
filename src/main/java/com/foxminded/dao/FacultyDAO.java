package com.foxminded.dao;

import com.foxminded.EntityNotFoundException;
import com.foxminded.ValidationException;
import com.foxminded.Faculty;
import java.util.List;
import java.util.UUID;

public interface FacultyDAO{

    Faculty save(Faculty faculty) throws ValidationException;

    Faculty update(Faculty faculty, String newName) throws ValidationException, EntityNotFoundException;

    void delete(UUID id) throws ValidationException, EntityNotFoundException;

    Faculty findByID(UUID id) throws CloneNotSupportedException;

    List<Faculty> findAllByUniversityID(UUID id) throws CloneNotSupportedException;

    Faculty findByIdAndUniversityId(UUID facultyID, UUID universityID) throws CloneNotSupportedException;
}
