package com.foxminded;

import java.util.HashMap;
import java.util.Map;

public class UniversityDAOImpl implements UniversityDAO{

    static private Map<Integer, University> universities = new HashMap<>();
    static int counter = 0;

    public University getUniversity(int id){
    return universities.get(id);
    }

    @Override
    public Map<Integer, University> getAll(){
        return universities;
    }

    @Override
    public void save(University university){
        counter++;
        universities.put(counter, university);
    }

    @Override
    public void update(University university, String newName){
        university.setName(newName);
    }

    @Override
    public void delete(int id){
        universities.remove(id);
    }
}
