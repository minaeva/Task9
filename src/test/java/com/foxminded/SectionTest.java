package com.foxminded;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class SectionTest extends FillingUniversityWithData {

    @Test
    public void createStudentMarks() throws ValidationException, EntityNotFoundException{
        Faculty faculty = university.createFaculty("Fa11");
        Group group = faculty.createGroup("Gr11");
        StudentCard student = group.takeStudent(new StudentCard("Bill"));
        Journal journal = group.getJournal();
        Subject subject = faculty.addSubject("Sub1");
        Section section = journal.createSection(subject);
        StudentMarks studentMarks = section.createStudentMarks(student);

        long studentMarksId = Helper.generateNewId();
        studentMarks.setId(studentMarksId);

        StudentMarks foundStudentMarks = section.findStudentMarks(student);
        assertEquals("Bill", foundStudentMarks.getStudentCard().getName());

        StudentMarks foundStudentMarksById = section.findStudentMarks(studentMarksId);
        assertEquals("Bill", foundStudentMarksById.getStudentCard().getName());
    }

    @Test
    public void removeStudentMarks() throws ValidationException, EntityNotFoundException{
        Faculty faculty = university.createFaculty("Fa22");
        Group group = faculty.createGroup("Gr22");
        StudentCard student = group.takeStudent(new StudentCard("Oscar"));
        Journal journal = group.getJournal();
        Subject subject = faculty.addSubject("Sub2");
        Section section = journal.createSection(subject);
        StudentMarks studentMarks = section.createStudentMarks(student);
        long studentMarksId = Helper.generateNewId();
        studentMarks.setId(studentMarksId);

        int beforeSize = section.findAllStudentMarks().size();
        section.removeStudentMarks(studentMarksId);
        int afterSize = section.findAllStudentMarks().size();
        assertEquals(beforeSize - 1, afterSize);
    }

    @Test
    public void calculateAverageMark_groupA() throws EntityNotFoundException{
        Faculty faculty = university.findFaculty(faculty1Id);
        Group group = faculty.findGroup(groupAId);
        Journal journal = group.getJournal();
        double average = journal.calculateAverageMark();
        double expected = 9.75;
        assertEquals(expected, average, 0.005);
    }

    @Test
    public void calculateAverageMark_groupZ() throws EntityNotFoundException{
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
        Group group = faculty.createGroup("G");
        StudentCard student = group.takeStudent(new StudentCard("STD"));
        Journal journal = group.getJournal();

        Subject subject = faculty.addSubject("French");
        Section section = journal.createSection(subject);
        section.createStudentMarks(student);

        int beforeSize = section.findStudentMarks(student).getMarks().size();
        section.addMark(student, 6);
        section.addMark(student, 8);
        int afterSize = section.findStudentMarks(student).getMarks().size();
        assertEquals(beforeSize + 2, afterSize);

        double actual = section.calculateAverageMark();
        double expected = 7.0;
        assertEquals(expected, actual, 0.005);
    }
}
