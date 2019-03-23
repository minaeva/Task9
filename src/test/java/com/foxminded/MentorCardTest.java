package com.foxminded;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class MentorCardTest {

    @Test
    public void addMark() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        Group group = faculty.createGroup("Group");
        long groupId = Helper.generateNewId();
        group.setId(groupId);

        StudentCard student = faculty.takeStudent("Ivan", groupId);
        Journal journal = group.getJournal();
        journal.setGroupId(groupId);

        Subject subject = faculty.addSubject("Math");
        Section section = journal.createSection(subject);
        section.setSubject(subject);

        StudentMarks studentMarks = section.createStudentMarks(student);
        long studentMarksId = Helper.generateNewId();
        studentMarks.setId(studentMarksId);

        int size = section.findStudentMarks(studentMarksId).getMarks().size();
        assertEquals(0, size);

        MentorCard mentor = faculty.hireMentor("Mozart");
        mentor.addMark(student, subject, (byte)5);

        size = section.findStudentMarks(studentMarksId).getMarks().size();
        assertEquals(1, size);
    }
}
