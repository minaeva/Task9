package com.foxminded;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class MentorCardTest extends FillingUniversityWithData{

    @Test
    public void addMark() throws ValidationException, EntityNotFoundException{
        Faculty faculty = university.findFaculty(faculty1Id);
        MentorCard mentor = faculty.hireMentor("Blokhin");
        long mentorId = Helper.generateNewId();
        mentor.setId(mentorId);

        Group group = faculty.createGroup("G78");
        StudentCard student = group.takeStudent(new StudentCard("Liza"));
        Subject subject = faculty.addSubject("SPORTS");
        Journal journal = group.getJournal();
        Section section = journal.createSection(subject);
        section.createStudentMarks(student);

        int beforeSize = section.findStudentMarks(student).getMarks().size();
        mentor.addMark(student, subject, journal, 5);
        mentor.addMark(student, subject, journal, 3);
        mentor.addMark(student, subject, journal, 3);
        mentor.addMark(student, subject, journal, 5);
        mentor.addMark(student, subject, journal, 4);
        mentor.addMark(student, subject, journal, 4);
        int afterSize = section.findStudentMarks(student).getMarks().size();
        assertEquals(beforeSize + 6, afterSize);

        double actual = section.calculateAverageMark();
        double expected = 4.0;
        assertEquals(expected, actual, 0.005);
    }
}
