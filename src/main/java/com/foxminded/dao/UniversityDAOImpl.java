package com.foxminded.dao;

import com.foxminded.EntityNotFoundException;
import com.foxminded.University;
import com.foxminded.ValidationException;
import java.util.*;

public class UniversityDAOImpl implements UniversityDAO {

    static private Map<UUID, University> universities = new HashMap<>();

    public University save(University university) throws ValidationException, CloneNotSupportedException{
        if (university == null) throw new ValidationException("Null university");
        if (university.getId() != null) throw new ValidationException("University with ID " + university.getId() + " already exists");
        for (UUID id: universities.keySet()){
            if (id == university.getId()) throw new ValidationException("University with ID " + university.getId() + " already exists");
        }
        UUID id = UUID.randomUUID();
        university.setId(id);
        universities.put(id, university);
        return university.clone();
    }

    public University update(University university, String newName) throws ValidationException, EntityNotFoundException, CloneNotSupportedException {
        if (university == null) throw new ValidationException("Null University");
        if (university.getId() == null) throw new EntityNotFoundException("Null ID => University doesn't exist");
        if (!universities.containsValue(university)) throw new EntityNotFoundException("University " + university.getName() + " doesn't exist");
        university.setName(newName);
        universities.put(university.getId(), university);
        return university.clone();
    }

    public void delete(UUID id) throws ValidationException, EntityNotFoundException {
        if (id == null) throw new ValidationException("Null ID");
        if (!universities.containsKey(id)) throw new EntityNotFoundException("University with id " + id + " doesn't exist");
        universities.remove(id);
    }

    public University findByID(UUID id) throws ValidationException, EntityNotFoundException, CloneNotSupportedException{
        if (id == null) throw new ValidationException("Null ID");
        if (!universities.containsKey(id)) throw new EntityNotFoundException("University with id " + id + " doesn't exist");
        return universities.get(id).clone();
    }

    public List<University> findAll(){
        List<University> allUniversities = new ArrayList<>(universities.values());
        return new ArrayList(allUniversities);
    }

}
