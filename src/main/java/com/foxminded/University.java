package com.foxminded;

import java.util.*;
import java.util.function.Predicate;
import lombok.Data;

@Data
public class University implements Cloneable {

    private long id;
    private String name;
    private List<Faculty> faculties = new ArrayList<>();

    public Faculty createFaculty(String facultyName) throws ValidationException{
        if (facultyName.equals("")) throw new ValidationException("Name cannot be empty");
        Predicate<Faculty> p = faculty -> faculty.getName() == facultyName;
        validateIfNew(faculties, p, "Faculty", name);
        Faculty newFaculty = new Faculty(facultyName);
        faculties.add(newFaculty);
        return newFaculty;
    }

    public Faculty updateFaculty(long facultyId, String newName) throws EntityNotFoundException{
        Faculty faculty = findFaculty(facultyId);
        faculty.setName(newName);
        return faculty;
    }

   public void dismantleFaculty(long facultyId) throws EntityNotFoundException{
        Faculty faculty = findFaculty(facultyId);
        faculties.remove(faculty);
    }

    public Faculty findFaculty(long facultyId) throws EntityNotFoundException{
        Predicate<Faculty> p = faculty -> faculty.getId() == facultyId;
        validateIfExists(faculties, p, "Faculty", facultyId);
        return faculties.stream().filter(p).findFirst().get();
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

    private <T> void validateIfNew(List<T> list, Predicate<T> predicate, String objectName, String name) throws ValidationException{
        if (list.stream().anyMatch(predicate))
            throw new ValidationException(objectName + " with name " + name + " already exists");
    }

    private <T> void validateIfExists(List<T> list, Predicate<T> predicate, String objectName, long id) throws EntityNotFoundException{
        if (list.stream().noneMatch(predicate))
            throw new EntityNotFoundException(objectName + " with id " + id + " doesn't exist");
    }
}
