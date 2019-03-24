package com.foxminded;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class StudentMarks {

    private long id;
    private StudentCard studentCard;
    private long sectionId;
    private List<Byte> marks = new ArrayList<>();

    public StudentMarks(StudentCard studentCard, long sectionId){
        this.studentCard = studentCard;
        this.sectionId =  sectionId;
    }

    public double calculateAverageMark() {
        if (marks.size() == 0) return 0;
        double result =  0.0;
        int counter = 0;
        for(Byte mark: marks){
            result += mark;
            counter++;
        }
        return result/counter;
    }

    public void addMark(byte mark) throws ValidationException{
        if ((mark < 1) || (mark > 12)) throw new ValidationException("Mark should be [1;12]");
        marks.add(mark);
    }
}
