package com.foxminded.dao;

import com.foxminded.EntityNotFoundException;
import com.foxminded.ValidationException;
import com.foxminded.Faculty;
import java.util.List;
import java.util.UUID;

public interface FacultyDAO{

    Faculty save(Faculty faculty) throws ValidationException, CloneNotSupportedException;

    Faculty update(Faculty faculty, String newName) throws ValidationException, EntityNotFoundException, CloneNotSupportedException;

    void delete(UUID id) throws ValidationException, EntityNotFoundException;

    Faculty findByID(UUID id) throws ValidationException, EntityNotFoundException, CloneNotSupportedException;

    Faculty findByIdAndUniversityId(UUID facultyID, UUID universityID) throws ValidationException, EntityNotFoundException, CloneNotSupportedException;

    List<Faculty> findByUniversityID(UUID id) throws CloneNotSupportedException, ValidationException;
}
