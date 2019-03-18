package com.foxminded;

import java.util.*;
import com.foxminded.dao.*;

public class University implements Cloneable {

    private UUID id;
    private String name;
    private Map<UUID, Faculty> faculties;
//    private UniversityDAO universityDAO = new UniversityDAOImpl();
    private FacultyDAO facultyDAO = new FacultyDAOImpl();

    public University() { }

    public University(String name) {
        this.name = name;
    }

    public University(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public Faculty createFaculty(String name) throws ValidationException, CloneNotSupportedException{
        Faculty newFaculty = new Faculty(name);
        Faculty savedFaculty = facultyDAO.save(newFaculty);
        faculties.put(savedFaculty.getId(), savedFaculty);
        return savedFaculty;
    }

    public Faculty updateFaculty(UUID id, String newName) throws EntityNotFoundException, ValidationException, CloneNotSupportedException{
        Faculty faculty = faculties.get(id);
        Faculty updatedFaculty = facultyDAO.update(faculty, newName);
        faculties.put(id, updatedFaculty);
        return updatedFaculty;
    }

    public void dismantleFaculty(UUID id) throws ValidationException, EntityNotFoundException{
        Faculty faculty = faculties.get(id);
        facultyDAO.delete(id);
        if (faculties.containsValue(faculty)) faculties.remove(faculty);
    }

    public Faculty findFaculty(UUID id) throws ValidationException, EntityNotFoundException, CloneNotSupportedException{
        UUID universityID = faculties.get(id).getUniversityID();
        return facultyDAO.findByIdAndUniversityId(id, universityID);
    }

    public List<Faculty> findFaculties() throws ValidationException{
        return facultyDAO.findByUniversityID(id);
    }

    public double calculateAverageMark(){ return 1.0; }

    public UUID getId(){
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object universityToCheck){
        if (universityToCheck == this) return true;
        if (!(universityToCheck instanceof University)) return false;
        University university = (University) universityToCheck;
        return university.getName().equals(name) && university.getId().equals(id);
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
