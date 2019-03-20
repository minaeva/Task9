package com.foxminded;

import lombok.Getter;
import lombok.Setter;

public class Subject {

    @Getter @Setter private long id;
    @Getter @Setter private String name;
    @Getter @Setter private long facultyId;

    public Subject(String name){
        this.name = name;
    }
}
