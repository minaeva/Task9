package com.foxminded;

import java.util.Map;

public interface FacultyDAO {
    Faculty getFaculty(int id);

    Map<Integer, Faculty> getAll();

    void save(Faculty faculty);

    void update(Faculty faculty, String newName);

    void delete(int id);

}
