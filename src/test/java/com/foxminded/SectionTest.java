package com.foxminded;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SectionTest{
    private University university;
    private Faculty faculty;
    private Group group;
    private Journal journal;
    private Section section;

    @Before
    public void before(){
        university = new University();
        faculty = university.createFaculty("FACULTY");
        group = faculty.createGroup("GROUP");
        journal = group.getJournal();
        section = journal.createSection("SECTION");
    }

    @Test
    public void createStudentMarks(){
        group.takeStudent(new StudentCard("Arina"));
        section.createStudentMarks("Arina");

        assertEquals(1, section.getStudentMarks().size());
    }

    @Test
    public void removeStudentMarks(){
        group.takeStudent(new StudentCard("Dan"));
        section.createStudentMarks("Dan");

        int beforeSize = section.getStudentMarks().size();
        section.removeStudentMarks("Dan");
        int afterSize = section.getStudentMarks().size();

        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void addMark(){
        group.takeStudent(new StudentCard("Frol"));
        section.createStudentMarks("Frol");

        int beforeSize = section.findStudentMarks("Frol").getMarks().size();
        section.addMark("Frol", 6);
        section.addMark("Frol", 8);
        int afterSize = section.findStudentMarks("Frol").getMarks().size();
        assertEquals(beforeSize + 2, afterSize);

        double actual = section.calculateAverageMark();
        double expected = 7.0;
        assertEquals(expected, actual, 0.005);
    }
}

