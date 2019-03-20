package com.foxminded;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

public class StudentMarks {

    @Getter @Setter private long id;
    @Getter @Setter private StudentCard studentCard;
    @Getter @Setter private long sectionId;
    private List<Integer> marks;

    public StudentMarks(){}

    public StudentMarks(StudentCard studentCard, long sectionId){
        this.studentCard = studentCard;
        this.sectionId =  sectionId;
    }

    public double calculateAverageMark() {
        //todo
        return 1.0;
    }

    public void addMark(int mark){
        //todo
    }
}
