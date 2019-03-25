package com.foxminded;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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

    public StudentMarks findStudentMarks(long marksId) throws EntityNotFoundException{
        Predicate<StudentMarks> p = studentMarks -> studentMarks.getId() == marksId;
        return Helper.validateIfExists(studentMarks, p, "Student marks", marksId);
    }

    public StudentMarks findStudentMarks(StudentCard studentCard) throws EntityNotFoundException{
        Predicate<StudentMarks> p = studentMarks -> studentMarks.getStudentCard().equals(studentCard);
        return Helper.validateIfExists(studentMarks, p, "Student marks for student", studentCard.getId());
    }

    public List<StudentMarks> findAllStudentMarks(){
        return studentMarks;
    }

    public void removeStudentMarks(long marksId) throws EntityNotFoundException{
        StudentMarks marks = findStudentMarks(marksId);
        studentMarks.remove(marks);
    }

    public void addMark(StudentCard studentCard, int mark) throws EntityNotFoundException, ValidationException{
        StudentMarks marks = findStudentMarks(studentCard);
        marks.addMark(mark);
    }

    public double calculateAverageMark() {
        if (studentMarks.size() == 0) return 0;
        double result = 0.0;
        int counter = 0;
        for (StudentMarks marks: studentMarks) {
            double midAverage = marks.calculateAverageMark();
            if (midAverage != 0){
                result += marks.calculateAverageMark();
                counter++;
            }
        }
        if (result == 0) return 0;
        return result/counter;
    }
}
