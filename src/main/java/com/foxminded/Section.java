package com.foxminded;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
public class Section {

    private long id;
    private Subject subject;
    private long journalId;
    private List<StudentMarks> studentMarks = new ArrayList<>();

    public Section(){}

    public Section(Subject subject){
        this.subject = subject;
    }

    public StudentMarks createStudentMarks(StudentCard studentCard){
        StudentMarks newStudentMarks = new StudentMarks(studentCard, this.id);
        studentMarks.add(newStudentMarks);
        return newStudentMarks;
    }

    public StudentMarks findStudentMarks(long marksId) throws EntityNotFoundException{
        Predicate<StudentMarks> p = m -> m.getId() == marksId;
        if (studentMarks.stream().noneMatch(p)) throw new EntityNotFoundException("Student marks with id " + marksId + " doesn't exist");
        return studentMarks.stream().filter(p).collect(Collectors.toList()).get(0);
    }

    public StudentMarks findStudentMarks(StudentCard studentCard) throws EntityNotFoundException{
        Predicate<StudentMarks> p = m -> m.getStudentCard() == studentCard;
        if (studentMarks.stream().noneMatch(p)) throw new EntityNotFoundException("Marks for student " + studentCard.getName() + " don't exist");
        return studentMarks.stream().filter(p).collect(Collectors.toList()).get(0);
    }

    public void removeStudentMarks(long marksId) throws EntityNotFoundException{
        StudentMarks marks = findStudentMarks(marksId);
        studentMarks.remove(marks);
    }

    public void addMark(StudentCard studentCard, byte mark) throws EntityNotFoundException, ValidationException{
        StudentMarks marks = findStudentMarks(studentCard);
        marks.addMark(mark);
    }

    public double calculateAverageMark() {
        if (studentMarks.size() == 0) return 0;
        double result = 0.0;
        int counter = 0;
        for (StudentMarks marks: studentMarks) {
            result += marks.calculateAverageMark();
            counter++;
        }
        return result/counter;
    }
}
