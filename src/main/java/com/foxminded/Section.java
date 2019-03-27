package com.foxminded;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Section {

    private long id;
    private Subject subject;
    private long journalId;
    private List<StudentMarks> studentMarks = new ArrayList<>();

    public Section(Subject subject){
        this.subject = subject;
    }

    public StudentMarks createStudentMarks(StudentCard studentCard){
        StudentMarks newStudentMarks = new StudentMarks(studentCard, this.id);
        studentMarks.add(newStudentMarks);
        return newStudentMarks;
    }

    public StudentMarks findStudentMarks(long marksId) throws IllegalArgumentException{
        return Helper.findObjectIfExists(studentMarks, studentMarks -> studentMarks.getId() == marksId, "Student marks", marksId);
    }

    public StudentMarks findStudentMarks(StudentCard studentCard) throws IllegalArgumentException{
        return Helper.findObjectIfExists(studentMarks, studentMarks -> studentMarks.getStudentCard().equals(studentCard), "Student marks for student", studentCard.getId());
    }

    public boolean removeStudentMarks(long marksId) throws IllegalArgumentException{
        return studentMarks.removeIf(studentMarks1 -> studentMarks1.getId() == marksId);
    }

    public void addMark(StudentCard studentCard, int mark) throws IllegalArgumentException{
        StudentMarks marks = findStudentMarks(studentCard);
        marks.addMark(mark);
    }

    public double calculateAverageMark() {
        if (studentMarks.size() == 0) {
            return 0;
        }
        double result = 0.0;
        int counter = 0;
        for (StudentMarks marks: studentMarks) {
            double midAverage = marks.calculateAverageMark();
            if (midAverage != 0){
                result += midAverage;
                counter++;
            }
        }
        if (result == 0) {
            return 0;
        }
        return result/counter;
    }
}
