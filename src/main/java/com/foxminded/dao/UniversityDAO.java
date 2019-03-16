package com.foxminded.dao;

import com.foxminded.University;
import java.util.Map;
import java.util.UUID;

public interface UniversityDAO {

    University save(University university);

    University update(University university, String newName);

    void delete(UUID id);

    University findByID(UUID id);

    Map<UUID, University> findAll();
}
