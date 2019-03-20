package com.foxminded;

import java.util.*;
import com.foxminded.dao.FacultyDAO;
import com.foxminded.dao.FacultyDAOImpl;
import lombok.Getter;
import lombok.Setter;

public class University implements Cloneable {

    @Getter @Setter private long id;
    @Getter @Setter private String name;
    private Map<Long, Faculty> faculties =  new HashMap<>();
    private FacultyDAO facultyDAO = new FacultyDAOImpl();

    public University() { }

    public University(String name) {
        this.name = name;
    }

    public University(long id, String name) {
        this.id =  id;
        this.name = name;
    }

    public Faculty createFaculty(String name) throws ValidationException, CloneNotSupportedException{
        Faculty newFaculty = new Faculty(name);
        newFaculty.setUniversityId(this.id);
        Faculty savedFaculty = facultyDAO.save(newFaculty);
        faculties.put(savedFaculty.getId(), savedFaculty);
        return newFaculty;
    }

    public Faculty updateFaculty(long id, String newName) throws EntityNotFoundException, ValidationException, CloneNotSupportedException{
        Faculty faculty = faculties.get(id);
        Faculty updatedFaculty = facultyDAO.update(faculty, newName);
        faculties.put(id, updatedFaculty);
        return updatedFaculty;
    }

    public void dismantleFaculty(long id) throws ValidationException, EntityNotFoundException{
        Faculty faculty = faculties.get(id);
        facultyDAO.delete(id);
        if (faculties.containsValue(faculty)) faculties.remove(faculty);
    }

    public Faculty findFaculty(long id) throws ValidationException, EntityNotFoundException, CloneNotSupportedException{
        long universityId = faculties.get(id).getUniversityId();
        return facultyDAO.findByIdAndUniversityId(id, universityId);
    }

    public List<Faculty> findFaculties() throws ValidationException{
        return facultyDAO.findByUniversityId(id);
    }

    public double calculateAverageMark(){
        //todo
         return 1.0;
    }

    @Override
    public boolean equals(Object universityToCheck){
        if (universityToCheck == this) return true;
        if (!(universityToCheck instanceof University)) return false;
        University university = (University) universityToCheck;
        return university.getName().equals(name) && university.getId() == id;
    }

    @Override
    public University clone() throws CloneNotSupportedException {
        try {
            return (University) super.clone();
        } catch (ClassCastException e) {
            return new University(this.getId(), this.getName());
        }
    }

}
