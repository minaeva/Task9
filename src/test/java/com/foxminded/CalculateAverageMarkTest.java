package com.foxminded;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class CalculateAverageMarkTest extends FillingDataForCalculateAverage {

    @Test
    public void calculateAverageMark_university(){
        double average = university.calculateAverageMark();

        double expected = 7.875;
        assertEquals(expected, average, 0.005);
    }

    @Test
    public void calculateAverageMark_faculty(){
        Faculty faculty = university.findFaculty(faculty1.getName());

        double average = faculty.calculateAverageMark();

        double expected = 9.75;
        assertEquals(expected, average, 0.005);
    }

    @Test
    public void calculateAverageMark_journal_zero(){
        Faculty faculty = university.createFaculty("Fa3");
        Group group = faculty.createGroup("Gr3");
        group.takeStudent(new StudentCard("Olga"));
        Journal journal = group.getJournal();
        faculty.addSubject("Sub3");
        Section section = journal.createSection("Sub3");
        section.createStudentMarks("Olga");

        double average = journal.calculateAverageMark();

        double expected = 0;
        assertEquals(expected, average, 0.005);
    }

    @Test
    public void calculateAverageMark_journalOnFaculty2(){
        Faculty faculty = university.findFaculty("Faculty2");
        Group group = faculty.findGroup("GroupZ");
        Journal journal = group.getJournal();

        double average = journal.calculateAverageMark();

        double expected = 6.0;
        assertEquals(expected, average, 0.005);
    }

    @Test
    public void calculateAverageMark_groupA() {
        Faculty faculty = university.findFaculty("Faculty1");
        Group group = faculty.findGroup("GroupA");
        Journal journal = group.getJournal();

        double average = journal.calculateAverageMark();

        double expected = 9.75;
        assertEquals(expected, average, 0.005);
    }

    @Test
    public void calculateAverageMark_groupZ(){
        Faculty faculty = university.findFaculty("Faculty2");
        Group group = faculty.findGroup("GroupZ");
        Journal journal = group.getJournal();

        double average = journal.calculateAverageMark();

        double expected = 6.0;
        assertEquals(expected, average, 0.005);
    }

    @Test
    public void calculateAverageMark_studentsMarks(){
        Faculty faculty = university.findFaculty("Faculty1");
        Group group = faculty.findGroup("GroupA");
        Journal journal = group.getJournal();
        Section section = journal.findSection("English");
        StudentMarks studentMarks = section.findStudentMarks("Ted");

        double average = studentMarks.calculateAverageMark();

        double expected = 9.0;
        assertEquals(expected, average, 0.005);
    }
}