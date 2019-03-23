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
        Journal createdJournal = new Journal();
        this.journal = createdJournal;
    }

    public StudentCard takeStudent(StudentCard studentCard){
        students.add(studentCard);
        studentCard.setGroupId(this.id);
        return studentCard;
    }

    public StudentCard findStudent(long studentId) throws EntityNotFoundException{
        Predicate<StudentCard> p = s -> s.getId() == studentId;
        Helper.validateIfExists(students, p, "Student", studentId);
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
}
