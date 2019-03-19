package com.foxminded;

import java.util.List;
import java.util.UUID;

public class StudentMarks {

    private UUID id;
    private StudentCard studentCard;
    private UUID sectionID;
    private List<Integer> marks;

    public StudentMarks(){}

    public StudentMarks(StudentCard studentCard, UUID sectionID){
        this.studentCard = studentCard;
        this.sectionID =  sectionID;
    }

    public double calculateAverageMark() {
        //todo
        return 1.0;
    }

    public void addMark(int mark){
        //todo
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public StudentCard getStudentCard() {
        return studentCard;
    }

    public void setStudentCard(StudentCard studentCard) {
        this.studentCard = studentCard;
    }

    public UUID getSectionID() {
        return sectionID;
    }

    public void setSectionID(UUID sectionID) {
        this.sectionID = sectionID;
    }
}
