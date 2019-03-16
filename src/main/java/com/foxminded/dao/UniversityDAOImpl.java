package com.foxminded.dao;

import com.foxminded.University;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UniversityDAOImpl implements UniversityDAO {

    static private Map<UUID, University> universities = new HashMap<>();

    public University save(University university){
        UUID id = UUID.randomUUID();
        university.setId(id);
        universities.put(id, university);
        return university;
    }

    public University update(University university, String newName){
        university.setName(newName);
        return university;
    }

    public void delete(UUID id){
        universities.remove(id);
    }

    public University findByID(UUID id){
        return universities.get(id);
    }

    public Map<UUID, University> findAll(){
        return universities;
    }

}
