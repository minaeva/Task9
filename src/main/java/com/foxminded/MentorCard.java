package com.foxminded;

import lombok.Data;

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

    public void addMark(StudentCard studentCard, Subject subject, Journal journal, int mark) throws ValidationException, EntityNotFoundException{
        journal.addMark(studentCard, subject, mark);
    }
}