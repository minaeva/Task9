package com.foxminded;

import lombok.Data;

@Data
public class Subject {

    private String name;

    public Subject(String name){
        this.name = name;
    }
}
