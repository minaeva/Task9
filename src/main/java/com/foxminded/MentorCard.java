package com.foxminded;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Data
public class MentorCard {

    private long id;
    private String name;
    private long subjectId;
    private long facultyId;

    public MentorCard(String name){
        this.name = name;
    }

    public void fire(){
        System.out.println("Permission received. Mentor " + this.name + " can now be fired");
    }

    public void addMark(StudentCard studentCard, Subject subject, Journal journal, byte mark) throws ValidationException, EntityNotFoundException{
        journal.addMark(studentCard, subject, mark);
    }
}