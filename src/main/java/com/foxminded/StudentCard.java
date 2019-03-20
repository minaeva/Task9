package com.foxminded;

import lombok.Getter;
import lombok.Setter;

public class StudentCard {

    @Getter @Setter private long id;
    @Getter @Setter private String name;
    @Getter @Setter private long groupId;
    @Getter @Setter private long facultyId;

    public StudentCard(String name){
        this.name = name;
    }

    public void dissmiss(){
        //todo
    }
}
