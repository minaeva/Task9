package com.foxminded;

import java.util.*;

public class University {

    private UUID id;
    private String name;
    private Map<UUID, Faculty> faculties;// = new HashMap<>();

    public Faculty createFaculty(String name){
        return new Faculty(name);
    }

    public Faculty updateFaculty(UUID id, String newName){
        Faculty faculty = faculties.get(id);
        faculty.setName(newName);
        return faculty;
    }

    public void dismantleFaculty(UUID id){}

    public Faculty findFaculty(UUID id){
        return faculties.get(id);
    }

    public List<Faculty> findFaculties(){
        return new ArrayList<>(faculties.values());
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

}
