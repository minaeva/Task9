package com.foxminded;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

public class MentorCard {

    @Getter @Setter private long id;
    @Getter @Setter private String name;
    @Getter @Setter private long subjectId;
    @Getter @Setter private long facultyId;
    @Getter @Setter private List<Journal> journals;

    public MentorCard(){}

    public MentorCard(String name){
        this.name = name;
    }

    public void fire(){
        //todo
    }

    public void addMark(StudentCard studentCard, Subject subject, int mark){
        //todo
    }
}