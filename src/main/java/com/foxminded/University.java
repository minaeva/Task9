package com.foxminded;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class University implements Cloneable {

    private long id;
    private String name;
    private List<Faculty> faculties = new ArrayList<>();

    public Faculty createFaculty(String facultyName) throws ValidationException{
        Predicate<Faculty> p = f -> f.getName() == facultyName;
        if (faculties.stream().anyMatch(p)) throw new ValidationException("Faculty with name " + facultyName + " already exists");
        if (facultyName == "") throw new ValidationException("Name cannot be empty");
        Faculty newFaculty = new Faculty(facultyName);
        faculties.add(newFaculty);
        return newFaculty;
    }

    public Faculty updateFaculty(long facultyId, String newName) throws EntityNotFoundException{
        Faculty faculty = findFaculty(facultyId);
        faculties.remove(faculty);
        faculty.setName(newName);
        faculties.add(faculty);
        return faculty;
    }

   public void dismantleFaculty(long facultyId) throws EntityNotFoundException{
        Faculty faculty = findFaculty(facultyId);
        faculties.remove(faculty);
    }

    public Faculty findFaculty(long facultyId) throws EntityNotFoundException{
        Predicate<Faculty> p = f -> f.getId() == facultyId;
        if (!faculties.stream().anyMatch(p)) throw new EntityNotFoundException("Faculty with id " + facultyId + " doesn't exist");
        return faculties.stream().filter(p).collect(Collectors.toList()).get(0);
    }

    public List<Faculty> findFaculties(){
        return faculties;
    }

    public double calculateAverageMark(){
        double result = 0.0;
        int counter = 0;
        for (Faculty faculty: faculties) {
            result += faculty.calculateAverageMark();
            counter++;
        }
        return result/counter;
    }

   @Override
    public boolean equals(Object universityToCheck) {
       if (universityToCheck == this) return true;
       if (!(universityToCheck instanceof University)) return false;
       University university = (University) universityToCheck;
       return university.getName().equals(name) && (university.getId() == id);
   }

       @Override
       public int hashCode() {
           int result = 17;
           result = 31 * result + name.hashCode();
           result = 31 * result + (int)id;
           return result;
       }

    @Override
    public University clone() throws CloneNotSupportedException {
        try {
            return (University) super.clone();
        } catch (ClassCastException e) {
            University newUniversity = new University();
            newUniversity.setId(this.id);
            newUniversity.setName(this.name);
            return newUniversity;
        }
    }

}
