package com.foxminded.dao;

import com.foxminded.EntityNotFoundException;
import com.foxminded.ValidationException;
import com.foxminded.Faculty;
import java.util.List;

public interface FacultyDAO{

    Faculty save(Faculty faculty) throws ValidationException, CloneNotSupportedException;

    Faculty update(Faculty faculty, String newName) throws ValidationException, EntityNotFoundException, CloneNotSupportedException;

    void delete(long id) throws ValidationException, EntityNotFoundException;

    Faculty findById(long id) throws ValidationException, EntityNotFoundException, CloneNotSupportedException;

    Faculty findByIdAndUniversityId(long facultyId, long universityId) throws ValidationException, EntityNotFoundException, CloneNotSupportedException;

    List<Faculty> findByUniversityId(long id) throws ValidationException;
}
