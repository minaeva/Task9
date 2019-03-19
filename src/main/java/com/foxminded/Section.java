package com.foxminded;

import java.util.List;
import java.util.UUID;

public class Section {

    private UUID id;
    private Subject subject;
    private UUID journalID;
    private List<StudentMarks> studentMarks;

    public Section(){}

    public Section(Subject subject){
        this.subject = subject;
    }

    public StudentMarks createStudentMarks(StudentCard studentCard){
        StudentMarks newStudentMarks = new StudentMarks(studentCard, this.id);
        studentMarks.add(newStudentMarks);
        return newStudentMarks;
    }

    public void removeStudentMarks(UUID id){
        //todo
    }

    public void addMark(StudentCard studentCard, byte mark){
        //todo
    }

    public double calculateAverageMark() {
        //todo
        return 1.0;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getJournalID() {
        return journalID;
    }

    public void setJournalID(UUID journalID) {
        this.journalID = journalID;
    }

    public List<StudentMarks> getStudentMarks() {
        return studentMarks;
    }

    public void setStudentMarks(List<StudentMarks> studentMarks) {
        this.studentMarks = studentMarks;
    }
}
