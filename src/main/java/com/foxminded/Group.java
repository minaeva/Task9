package com.foxminded;

import lombok.Data;
import java.util.*;

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

    public StudentCard findStudent(long studentId) throws IllegalArgumentException{
        return Helper.validateObjectExists(students, studentCard -> studentCard.getId() == studentId, "Student", studentId);
    }

    public boolean dismissStudent(long studentId) throws IllegalArgumentException{
        return students.removeIf(studentCard -> studentCard.getId() == studentId);
    }
}
