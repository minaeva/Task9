package com.foxminded;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import static com.foxminded.Validator.*;

@Data
public class Section {

    private String sectionName;
    private List<StudentMarks> studentMarks = new ArrayList<>();

    public Section(String subjectName){
        this.sectionName = subjectName;
    }

    public StudentMarks createStudentMarks(String studentName){
        StudentMarks newStudentMarks = new StudentMarks(studentName, this.sectionName);
        studentMarks.add(newStudentMarks);
        return newStudentMarks;
    }

    public StudentMarks findStudentMarks(String studentName){
        return findObjectByNameIfExists(studentMarks,
                studentMarks -> studentMarks.getStudentName().equals(studentName),
                "Student marks",
                studentName);
    }

    public boolean removeStudentMarks(String studentName){
        return studentMarks.removeIf(marksToRemove -> marksToRemove.getStudentName().equals(studentName));
    }

    public void addMark(String studentName, int mark){
        StudentMarks marks = findStudentMarks(studentName);
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
        return (result == 0) ? 0 : result/counter;
    }
}
