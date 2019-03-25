package com.foxminded;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StudentMarksTest extends FillingUniversityWithData{

 /*   @Test
    public void calculateAverageMark_A1Eng() throws EntityNotFoundException{
        Faculty faculty = university.findFaculty(faculty1Id);
        Group group = faculty.findGroup(groupAId);
        Journal journal = group.getJournal();
        Section section = journal.findSection(sectionAEngId);
        StudentMarks studentMarks = section.findStudentMarks(studentMarks1AEngId);
        double average = studentMarks.calculateAverageMark();
        double expected = 9.0;
        assertEquals(expected, average, 0.005);
    }

    @Test
    public void addMark() throws ValidationException, EntityNotFoundException{
        Faculty faculty = university.findFaculty(faculty1Id);
        Group group = faculty.findGroup(groupAId);
        Journal journal = group.getJournal();
        Section section = journal.findSection(sectionAMathId);
        StudentMarks studentMarks = section.findStudentMarks(studentMarks1AMathId);

        int beforeSize = section.findStudentMarks(studentMarks1AMathId).getMarks().size();
        studentMarks.addMark(8);
        studentMarks.addMark(12);
        int afterSize = section.findStudentMarks(studentMarks1AMathId).getMarks().size();
        assertEquals(beforeSize + 2, afterSize);

        double actual = studentMarks.calculateAverageMark();
        double expected = 9.5;
        assertEquals(expected, actual, 0.005);
    }

  */
}

