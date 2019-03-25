package com.foxminded;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MentorCardTest extends FillingUniversityWithData{

    @Test
    public void addMark() throws ValidationException, EntityNotFoundException{
        Faculty faculty = university.findFaculty(faculty1Id);
        Group group = faculty.findGroup(groupAId);
        StudentCard student = group.findStudent(student1AId);
        Journal journal = group.getJournal();
        Section section = journal.findSection(sectionAEngId);
        Subject subject = faculty.findSubject(subjectEnglishF2Id);

        int beforeSize = section.findStudentMarks(studentMarks1AEngId).getMarks().size();

        engMentor.addMark(student, subject, journal, 5);
        engMentor.addMark(student, subject, journal, 3);
        engMentor.addMark(student, subject, journal, 3);
        engMentor.addMark(student, subject, journal, 5);
        engMentor.addMark(student, subject, journal, 4);


        int afterSize = section.findStudentMarks(studentMarks1AEngId).getMarks().size();
        assertEquals(beforeSize + 5, afterSize);

        double actual = section.calculateAverageMark();
        double expected = 4.0;
        assertEquals(expected, actual, 0.005);
    }
}
