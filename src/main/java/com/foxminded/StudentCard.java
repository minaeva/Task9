package com.foxminded;

import lombok.Data;

@Data
public class StudentCard {

    private long id;
    private String name;
    private long groupId;
    private long facultyId;

    public StudentCard(String name){
        this.name = name;
    }
}
