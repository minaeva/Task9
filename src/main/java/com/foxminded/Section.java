package com.foxminded;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

public class Section {

    @Getter @Setter private long id;
    @Getter @Setter private Subject subject;
    @Getter @Setter private long journalId;
    @Getter @Setter private List<StudentMarks> studentMarks;

    public Section(){}

    public Section(Subject subject){
        this.subject = subject;
    }

    public StudentMarks createStudentMarks(StudentCard studentCard){
        StudentMarks newStudentMarks = new StudentMarks(studentCard, this.id);
        studentMarks.add(newStudentMarks);
        return newStudentMarks;
    }

    public void removeStudentMarks(long id){
        //todo
    }

    public void addMark(StudentCard studentCard, byte mark){
        //todo
    }

    public double calculateAverageMark() {
        //todo
        return 1.0;
    }
}
