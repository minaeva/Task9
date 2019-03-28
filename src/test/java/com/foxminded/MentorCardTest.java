package com.foxminded;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class MentorCardTest {

    private Faculty faculty;
    private Group group;
    private Journal journal;
    private Section section;
    private MentorCard mentor;

    @Before
    public void before(){
        faculty = new Faculty("F4M");
        group = faculty.createGroup("GROUP");
        group.takeStudent(new StudentCard("Liza"));
        faculty.addSubject("SPORTS");
        journal = group.getJournal();
        section = journal.createSection("SPORTS");
        section.createStudentMarks("Liza");
        mentor = faculty.hireMentor("Mozart");
    }

    @Test
    public void addMark(){
        int beforeSize = section.findStudentMarks("Liza").getMarks().size();
        mentor.addMark("Liza", "SPORTS", journal, 5);
        mentor.addMark("Liza", "SPORTS", journal, 3);
        mentor.addMark("Liza", "SPORTS", journal, 3);
        mentor.addMark("Liza", "SPORTS", journal, 5);
        mentor.addMark("Liza", "SPORTS", journal, 4);
        mentor.addMark("Liza", "SPORTS", journal, 4);
        int afterSize = section.findStudentMarks("Liza").getMarks().size();

        assertEquals(beforeSize + 6, afterSize);
        double actual = section.calculateAverageMark();
        double expected = 4.0;
        assertEquals(expected, actual, 0.005);
    }
}