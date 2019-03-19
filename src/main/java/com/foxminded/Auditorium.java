package com.foxminded;

import java.util.UUID;

public class Auditorium {

    private UUID id;
    private int number;
    private UUID facultyID;

    public Auditorium(){}

    public Auditorium(int number){ this.number = number;}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public UUID getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(UUID facultyID) {
        this.facultyID = facultyID;
    }
}
