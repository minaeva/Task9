package com.foxminded;

import org.junit.Before;

public class FillingDataForCalculateAverage {
    protected University university;
    protected Faculty faculty1;
    protected Faculty faculty2;
    protected Group groupA;
    protected Group groupB;
    protected Group groupC;
    protected Group groupY;
    protected Group groupZ;
    protected StudentCard student1A;
    protected StudentCard student2A;
    protected StudentCard student3A;
    protected StudentCard student1Z;
    protected Journal journalA;
    protected Journal journalZ;
    protected Subject subjectMathF1;
    protected Subject subjectEnglishF1;
    protected Subject subjectMathF2;
    protected Subject subjectEnglishF2;
    protected Section sectionAMath;
    protected Section sectionAEng;
    protected Section sectionZMath;
    protected Section sectionZEng;
    protected StudentMarks studentMarks1AMath;
    protected StudentMarks studentMarks1AEng;
    protected StudentMarks studentMarks2AMath;
    protected StudentMarks studentMarks2AEng;
    protected StudentMarks studentMarks3AMath;
    protected StudentMarks studentMarks3AEng;
    protected StudentMarks studentMarks1ZMath;
    protected StudentMarks studentMarks1ZEng;
    protected MentorCard engMentor;
    protected MentorCard mathMentor;
    protected Auditorium auditorium;

    @Before
    public void fillData() throws IllegalArgumentException {
        createFaculties();
        createStudents();
        createJournals();
        createSubjects();
        createSections();
        createStudentMarks();
        createMentors();
        createMarks();
        createAuditoria();
    }

     private void createFaculties() throws IllegalArgumentException {
         university = new University();
         faculty1 = university.createFaculty("Faculty1");

         faculty2 = university.createFaculty("Faculty2");

         groupA = faculty1.createGroup("GroupA");

         groupB = faculty1.createGroup("GroupB");

         groupC = faculty1.createGroup("GroupC");

         groupY = faculty2.createGroup("GroupY");

         groupZ = faculty2.createGroup("GroupZ");
     }

    private void createStudents(){
        StudentCard studentTed = new StudentCard("Ted");
        student1A = groupA.takeStudent(studentTed);

        StudentCard studentBen = new StudentCard("Ben");
        student2A = groupA.takeStudent(studentBen);

        StudentCard studentAnn = new StudentCard("Ann");
        student3A = groupA.takeStudent(studentAnn);

        StudentCard studentMargaret = new StudentCard("Margaret");
        student1Z = groupZ.takeStudent(studentMargaret);
    }

    private void createJournals(){
        journalA = groupA.getJournal();

        journalZ = groupZ.getJournal();
    }

    private void createSubjects() throws IllegalArgumentException {
        subjectMathF1 = faculty1.addSubject("Math");

        subjectEnglishF1 = faculty1.addSubject("English");

        subjectMathF2 = faculty2.addSubject("Math");

        subjectEnglishF2 = faculty2.addSubject("English");
    }

    private void createSections(){
        sectionAMath = journalA.createSection("Math");

        sectionAEng = journalA.createSection("English");

        sectionZMath = journalZ.createSection("Math");

        sectionZEng = journalZ.createSection("English");
    }

    private void createStudentMarks(){
        studentMarks1AMath = sectionAMath.createStudentMarks("Ted");

        studentMarks1AEng = sectionAEng.createStudentMarks("Ted");

        studentMarks2AMath = sectionAMath.createStudentMarks("Ben");

        studentMarks2AEng = sectionAEng.createStudentMarks("Ben");

        studentMarks3AMath = sectionAMath.createStudentMarks("Ann");

        studentMarks3AEng = sectionAEng.createStudentMarks("Ann");

        studentMarks1ZMath = sectionZMath.createStudentMarks("Margaret");

        studentMarks1ZEng = sectionZEng.createStudentMarks("Margaret");
    }

    private void createMentors() throws IllegalArgumentException {
        engMentor = faculty1.hireMentor("ENGLISH teacher");
        mathMentor = faculty1.hireMentor("MATH teacher");
    }

    private void createMarks() throws IllegalArgumentException {
        //1A Math average = 9
        studentMarks1AMath.addMark(10);
        studentMarks1AMath.addMark(8);

        //1A Eng average = 9
        studentMarks1AEng.addMark(10);
        studentMarks1AEng.addMark(8);

        //2A Math average = 10
        studentMarks2AMath.addMark(11);
        studentMarks2AMath.addMark(9);
        studentMarks2AMath.addMark(11);
        studentMarks2AMath.addMark(9);

        //3A Eng average = 11
        studentMarks3AEng.addMark(12);
        studentMarks3AEng.addMark(10);
        studentMarks3AEng.addMark(10);
        studentMarks3AEng.addMark(12);

        //1Z Math average = 8
        studentMarks1ZMath.addMark(6);
        studentMarks1ZMath.addMark(10);
        studentMarks1ZMath.addMark(8);
        studentMarks1ZMath.addMark(8);
        studentMarks1ZMath.addMark(10);
        studentMarks1ZMath.addMark(6);

        //1Z Eng average = 4
        studentMarks1ZEng.addMark(4);
        studentMarks1ZEng.addMark(2);
        studentMarks1ZEng.addMark(8);
        studentMarks1ZEng.addMark(2);
    }

    private void createAuditoria() throws IllegalArgumentException {
        auditorium = faculty1.addAuditorium(111);
    }
}
