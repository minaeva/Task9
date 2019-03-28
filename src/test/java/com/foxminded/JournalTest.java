package com.foxminded;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class JournalTest{

    private Group group;
    private Journal journal;

    @Before
    public void before(){
        group = new Group("GROUP");
        journal = group.getJournal();
    }

    @Test
    public void createSection(){
        journal.createSection("SUB1");

        assertEquals(1, journal.getSections().size());
    }

    @Test
    public void removeSection(){
        journal.createSection("SUB2");

        int beforeSize = journal.getSections().size();
        journal.removeSection("SUB2");
        int afterSize = journal.getSections().size();

        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void addMark(){
        group.takeStudent(new StudentCard("Zack"));
        Section section = journal.createSection("SUB1");
        section.createStudentMarks("Zack");

        int beforeSize = section.findStudentMarks("Zack").getMarks().size();
        journal.addMark("Zack", "SUB1", 7);
        journal.addMark("Zack", "SUB1", 9);
        int afterSize = section.findStudentMarks("Zack").getMarks().size();

        assertEquals(beforeSize + 2, afterSize);
    }
}
