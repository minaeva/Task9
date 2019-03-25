package com.foxminded;

import org.junit.Before;

public class FillingUniversityWithData {
    protected University university;
    Faculty faculty1;
    Faculty faculty2;
    Group groupA;
    Group groupB;
    Group groupC;
    Group groupY;
    Group groupZ;
    StudentCard student1A;
    StudentCard student2A;
    StudentCard student3A;
    StudentCard student1Z;
    Journal journalA;
    Journal journalZ;
    Subject subjectMathF1;
    Subject subjectEnglishF1;
    Subject subjectMathF2;
    Subject subjectEnglishF2;
    Section sectionAMath;
    Section sectionAEng;
    Section sectionZMath;
    Section sectionZEng;
    StudentMarks studentMarks1AMath;
    StudentMarks studentMarks1AEng;
    StudentMarks studentMarks2AMath;
    StudentMarks studentMarks2AEng;
    StudentMarks studentMarks3AMath;
    StudentMarks studentMarks3AEng;
    StudentMarks studentMarks1ZMath;
    StudentMarks studentMarks1ZEng;
    MentorCard engMentor;
    MentorCard mathMentor;
    Auditorium auditorium;

    @Before
    public void fillData() throws ValidationException {
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
         long faculty1Id = Helper.generateNewId();
         faculty1.setId(faculty1Id);

         faculty2 = university.createFaculty("Faculty2");
         long faculty2Id = Helper.generateNewId();
         faculty2.setId(faculty2Id);

         groupA = faculty1.createGroup("GroupA");
         long groupAId = Helper.generateNewId();
         groupA.setId(groupAId);

         groupB = faculty1.createGroup("GroupB");
         long groupBId = Helper.generateNewId();
         groupB.setId(groupBId);

         groupC = faculty1.createGroup("GroupC");
         long groupCId = Helper.generateNewId();
         groupC.setId(groupCId);

         groupY = faculty2.createGroup("GroupY");
         long groupYId = Helper.generateNewId();
         groupY.setId(groupYId);

         groupZ = faculty2.createGroup("GroupZ");
         long groupZId = Helper.generateNewId();
         groupZ.setId(groupZId);
     }

    private void createStudents(){
        StudentCard studentTed = new StudentCard("Ted");
        student1A = groupA.takeStudent(studentTed);
        long student1AId = Helper.generateNewId();
        student1A.setId(student1AId);

        StudentCard studentBen = new StudentCard("Ben");
        student2A = groupA.takeStudent(studentBen);
        long student2AId = Helper.generateNewId();
        student2A.setId(student2AId);

        StudentCard studentAnn = new StudentCard("Ann");
        student3A = groupA.takeStudent(studentAnn);
        long student3AId = Helper.generateNewId();
        student3A.setId(student3AId);

        StudentCard studentMargaret = new StudentCard("Margaret");
        student1Z = groupZ.takeStudent(studentMargaret);
        long student1ZId = Helper.generateNewId();
        student1Z.setId(student1ZId);
    }

    private void createJournals(){
        journalA = groupA.getJournal();
        long journalAId = Helper.generateNewId();
        journalA.setId(journalAId);

        journalZ = groupZ.getJournal();
        long journalZId = Helper.generateNewId();
        journalZ.setId(journalZId);
    }

    private void createSubjects() throws IllegalArgumentException {
        subjectMathF1 = faculty1.addSubject("Math");
        long subjectMathF1Id = Helper.generateNewId();
        subjectMathF1.setId(subjectMathF1Id);

        subjectEnglishF1 = faculty1.addSubject("English");
        long subjectEnglishF1Id = Helper.generateNewId();
        subjectEnglishF1.setId(subjectEnglishF1Id);

        subjectMathF2 = faculty1.addSubject("Math");
        long subjectMathF2Id = Helper.generateNewId();
        subjectMathF2.setId(subjectMathF2Id);

        subjectEnglishF2 = faculty1.addSubject("English");
        long subjectEnglishF2Id = Helper.generateNewId();
        subjectEnglishF2.setId(subjectEnglishF2Id);
    }

    private void createSections(){
        sectionAMath = journalA.createSection(subjectMathF1);
        long sectionAMathId = Helper.generateNewId();
        sectionAMath.setId(sectionAMathId);

        sectionAEng = journalA.createSection(subjectEnglishF1);
        long sectionAEngId = Helper.generateNewId();
        sectionAEng.setId(sectionAEngId);

        sectionZMath = journalZ.createSection(subjectMathF2);
        long sectionZMathId = Helper.generateNewId();
        sectionZMath.setId(sectionZMathId);

        sectionZEng = journalZ.createSection(subjectEnglishF2);
        long sectionZEngId = Helper.generateNewId();
        sectionZEng.setId(sectionZEngId);
    }

    private void createStudentMarks(){
        studentMarks1AMath = sectionAMath.createStudentMarks(student1A);
        long studentMarks1AMathId = Helper.generateNewId();
        studentMarks1AMath.setId(studentMarks1AMathId);

        studentMarks1AEng = sectionAEng.createStudentMarks(student1A);
        long studentMarks1AEngId = Helper.generateNewId();
        studentMarks1AEng.setId(studentMarks1AEngId);

        studentMarks2AMath = sectionAMath.createStudentMarks(student2A);
        long studentMarks2AMathId = Helper.generateNewId();
        studentMarks2AMath.setId(studentMarks2AMathId);

        studentMarks2AEng = sectionAEng.createStudentMarks(student2A);
        long studentMarks2AEngId = Helper.generateNewId();
        studentMarks2AEng.setId(studentMarks2AEngId);

        studentMarks3AMath = sectionAMath.createStudentMarks(student3A);
        long studentMarks3AMathId = Helper.generateNewId();
        studentMarks3AMath.setId(studentMarks3AMathId);

        studentMarks3AEng = sectionAEng.createStudentMarks(student3A);
        long studentMarks3AEngId = Helper.generateNewId();
        studentMarks3AEng.setId(studentMarks3AEngId);

        studentMarks1ZMath = sectionZMath.createStudentMarks(student1Z);
        long studentMarks1ZMathId = Helper.generateNewId();
        studentMarks1ZMath.setId(studentMarks1ZMathId);

        studentMarks1ZEng = sectionZEng.createStudentMarks(student1Z);
        long studentMarks1ZEngId = Helper.generateNewId();
        studentMarks1ZEng.setId(studentMarks1ZEngId);
    }

    private void createMentors() throws ValidationException {
        engMentor = faculty1.hireMentor("ENGLISH teacher");
        long engMentorId = Helper.generateNewId();
        engMentor.setId(engMentorId);
        mathMentor = faculty1.hireMentor("MATH teacher");
        long mathMentorId = Helper.generateNewId();
        mathMentor.setId(mathMentorId);
    }

    private void createMarks() throws ValidationException {
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

    private void createAuditoria() throws ValidationException {
        auditorium = faculty1.addAuditorium(111);
        long auditoriumId = Helper.generateNewId();
        auditorium.setId(auditoriumId);
    }
}
