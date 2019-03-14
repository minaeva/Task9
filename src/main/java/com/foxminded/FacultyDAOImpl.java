package com.foxminded;

import java.util.HashMap;
import java.util.Map;

public class FacultyDAOImpl implements FacultyDAO {

    static private Map<Integer, Faculty> faculties = new HashMap<>();
    static int counter = 0;

    public Faculty getFaculty(int id) {
        return faculties.get(id);
    }

    @Override
    public Map<Integer, Faculty> getAll() {
        return faculties;
    }

    @Override
    public void save(Faculty faculty) {
        counter++;
        faculties.put(counter, faculty);
    }

    @Override
    public void update(Faculty faculty, String newName) {
        faculty.setName(newName);
    }

    @Override
    public void delete(int id) {
        faculties.remove(id);
    }

}


