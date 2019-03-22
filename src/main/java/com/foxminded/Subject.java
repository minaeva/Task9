package com.foxminded;

import lombok.Data;

@Data
public class Subject {

    private long id;
    private String name;
    private long facultyId;

    public Subject(String name){
        this.name = name;
    }
}
