package com.foxminded;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class JournalTest extends FillingUniversityWithData{

 /*   @Test
    public void createSection() throws ValidationException, EntityNotFoundException{
        Faculty faculty = university.createFaculty("Fa1");
        Group group = faculty.createGroup("Gr1");
        Journal journal = group.getJournal();
        Subject subject = faculty.addSubject("Sub1");
        Section section = journal.createSection(subject);

        long sectionId = Helper.generateNewId();
        section.setId(sectionId);
        Section foundSection = journal.findSection(sectionId);
        assertEquals(section, foundSection);
    }

    @Test
    public void removeSection() throws ValidationException, EntityNotFoundException{
        Faculty faculty = university.createFaculty("Fa2");
        Group group = faculty.createGroup("Gr2");
        Journal journal = group.getJournal();
        Subject subject = faculty.addSubject("Sub2");
        Section section = journal.createSection(subject);

        int beforeSize = journal.getSections().size();
        long sectionId = Helper.generateNewId();
        section.setId(sectionId);
        journal.removeSection(sectionId);

        int afterSize = journal.getSections().size();
        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void calculateAverageMark_zero() throws ValidationException{
        Faculty faculty = university.createFaculty("Fa3");
        Group group = faculty.createGroup("Gr3");
        StudentCard student = group.takeStudent(new StudentCard("Maria"));
        Journal journal = group.getJournal();
        Subject subject = faculty.addSubject("Sub3");
        Section section = journal.createSection(subject);
        section.createStudentMarks(student);

        double average = journal.calculateAverageMark();
        double expected = 0;
        assertEquals(expected, average, 0.005);
    }

    @Test
    public void calculateAverageMark_faculty2() throws EntityNotFoundException{
        Faculty faculty = university.findFaculty(faculty2Id);
        Group group = faculty.findGroup(groupZId);
        Journal journal = group.getJournal();
        double average = journal.calculateAverageMark();
        double expected = 6.0;
        assertEquals(expected, average, 0.005);
    }

    @Test
    public void addMark() throws ValidationException, EntityNotFoundException{
        Faculty faculty = university.findFaculty(faculty1Id);
        Group group = faculty.createGroup("GG");
        StudentCard student = group.takeStudent(new StudentCard("STD"));
        Journal journal = group.getJournal();
        Subject subject = faculty.addSubject("Painting");
        Section section = journal.createSection(subject);
        section.createStudentMarks(student);

        int beforeSize = section.findStudentMarks(student).getMarks().size();
        journal.addMark(student, subject, 7);
        journal.addMark(student, subject, 9);
        int afterSize = section.findStudentMarks(student).getMarks().size();
        assertEquals(beforeSize + 2, afterSize);

        double actual = journal.calculateAverageMark();
        double expected = 8.0;
        assertEquals(expected, actual, 0.005);
    }

  */
}
