package com.foxminded.dao;

import com.foxminded.EntityNotFoundException;
import com.foxminded.University;
import com.foxminded.ValidationException;
import java.util.List;

public interface UniversityDAO {

    University save(University university) throws ValidationException, CloneNotSupportedException;

    University update(University university, String newName) throws ValidationException, EntityNotFoundException, CloneNotSupportedException;

    void delete(long id) throws ValidationException, EntityNotFoundException;

    University findById(long id) throws ValidationException, EntityNotFoundException, CloneNotSupportedException;

    List<University> findAll();
}

