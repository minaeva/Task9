package com.foxminded;

import lombok.Getter;
import lombok.Setter;

public class Auditorium {

    @Getter @Setter private long id;
    @Getter @Setter private int number;
    @Getter @Setter private long facultyID;

    public Auditorium(){}

    public Auditorium(int number){ this.number = number;}
}
