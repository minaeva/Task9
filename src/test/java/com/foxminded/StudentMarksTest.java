package com.foxminded;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StudentMarksTest{
    private University university;
    private Faculty faculty;
    private Group group;
    private StudentCard student;
    private Journal journal;
    private Section section;
    private StudentMarks studentMarks;

    @Before
    public void before(){
        university = new University();
        faculty = university.createFaculty("FACULTY");
        group = faculty.createGroup("GROUP");
        student = group.takeStudent(new StudentCard("Maria"));
        journal = group.getJournal();
        section = journal.createSection("SECTION");
        studentMarks = section.createStudentMarks("Maria");
    }

    @Test
    public void addMark(){
        int beforeSize = section.findStudentMarks("Maria").getMarks().size();
        studentMarks.addMark(8);
        studentMarks.addMark(12);
        int afterSize = section.findStudentMarks("Maria").getMarks().size();
        assertEquals(beforeSize + 2, afterSize);

        double actual = studentMarks.calculateAverageMark();
        double expected = 10;
        assertEquals(expected, actual, 0.005);
    }
}

