package com.foxminded;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JournalTest {

    @Test
    public void createSection() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        Group group = faculty.createGroup("Group");
        Journal journal = group.getJournal();
        Subject subject = faculty.addSubject("FuncAnalysis");
        Section section = journal.createSection(subject);

        long sectionId = Helper.generateNewId();
        section.setId(sectionId);
        Section foundSection = journal.findSection(sectionId);
        assertEquals(section, foundSection);
    }

    @Test
    public void removeSection() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        Group group = faculty.createGroup("Group");
        Journal journal = group.getJournal();
        Subject subject = faculty.addSubject("FuncAnalysis");
        Section section = journal.createSection(subject);

        int size = journal.getSections().size();
        assertEquals(1, size);

        long sectionId = Helper.generateNewId();
        section.setId(sectionId);
        journal.removeSection(sectionId);

        size = journal.getSections().size();
        assertEquals(0, size);
    }
}
