package com.foxminded;

import java.util.Map;

public interface UniversityDAO {
    University getUniversity(int id);

    Map<Integer, University> getAll();

    void save(University university);

    void update(University university, String newName);

    void delete(int id);
}
