package com.foxminded.dao;

import com.foxminded.University;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UniversityDAOImpl implements UniversityDAO {

    static private Map<UUID, University> universities = new HashMap<>();

    public void save(University university){
        UUID uuid = UUID.randomUUID();
        universities.put(uuid, university);
    }

    public void update(University university, String newName){
        university.setName(newName);
    }

    public void delete(UUID id){
        universities.remove(id);
    }

    public University getUniversity(UUID id){
        return universities.get(id);
    }

    public Map<UUID, University> getAll(){
        return universities;
    }

}
