package com.foxminded;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StudentMarksTest{

    private Group group;
    private Section section;
    private StudentMarks studentMarks;

    @Before
    public void before(){
        group = new Group("GROUP");
        group.takeStudent(new StudentCard("Maria"));
        section = group.getJournal().createSection("SECTION");
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