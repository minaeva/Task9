package com.foxminded;

import lombok.Data;
import java.util.*;
import java.util.function.Predicate;

@Data
public class Group {

    private long id;
    private String name;
    private long facultyId;
    private List<StudentCard> students = new ArrayList<>();
    private Journal journal;

    public Group(String name){
        this.name = name;
        Journal journal = new Journal();
        journal.setGroupId(this.id);
        journal.setFacultyId(this.getFacultyId());
        this.journal = journal;
    }

    public StudentCard takeStudent(StudentCard studentCard){
        students.add(studentCard);
        studentCard.setGroupId(this.id);
        return studentCard;
    }

    public StudentCard findStudent(long studentId) throws EntityNotFoundException{
        Predicate<StudentCard> p = s -> s.getId() == studentId;
        validateIfExists(students, p, "Student", studentId);
        return students.stream().filter(p).findFirst().get();
    }

    public void dismissStudent(long studentId) throws EntityNotFoundException{
        StudentCard student = findStudent(studentId);
        student.dismiss();
        students.remove(student);
    }

    public List<StudentCard> findStudents(){
        return students;
    }

    //???
    public void dismantle(){
        for (StudentCard student: students) {
            student.setGroupId(0);
        }
    }

    private <T> void validateIfExists(List<T> list, Predicate<T> predicate, String objectName, long id) throws EntityNotFoundException{
        if (list.stream().noneMatch(predicate))
            throw new EntityNotFoundException(objectName + " with id " + id + " doesn't exist");
    }
}
