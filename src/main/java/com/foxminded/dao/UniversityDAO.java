package com.foxminded.dao;

import com.foxminded.University;
import java.util.Map;
import java.util.UUID;

public interface UniversityDAO {

    void save(University university);

    void update(University university, String newName);

    void delete(UUID id);

    University getUniversity(UUID id);

    Map<UUID, University> getAll();
}
