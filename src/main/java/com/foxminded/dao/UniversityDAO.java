package com.foxminded.dao;

import com.foxminded.EntityNotFoundException;
import com.foxminded.University;
import com.foxminded.ValidationException;
import java.util.List;
import java.util.UUID;

public interface UniversityDAO {

    University save(University university) throws ValidationException, CloneNotSupportedException;

    University update(University university, String newName) throws ValidationException, EntityNotFoundException, CloneNotSupportedException;

    void delete(UUID id) throws ValidationException, EntityNotFoundException;

    University findByID(UUID id) throws ValidationException, EntityNotFoundException, CloneNotSupportedException;

    List<University> findAll();
}

