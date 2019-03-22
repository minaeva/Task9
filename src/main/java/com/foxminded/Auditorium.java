package com.foxminded;

import lombok.Data;

@Data
public class Auditorium {

    private long id;
    private int number;
    private long facultyID;

    public Auditorium(){}

    public Auditorium(int number){ this.number = number;}
}
