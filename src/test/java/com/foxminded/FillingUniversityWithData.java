package com.foxminded;

import org.junit.BeforeClass;

public class FillingUniversityWithData {
    static final protected University university =  new University();
    static long faculty1Id, faculty2Id, groupAId, groupBId, groupCId, groupYId, groupZId,
            student1AId, student2AId, student3AId, student1ZId,
            journalAId, journalZId,
            engMentorId , mathMentorId,
            subjectMathF1Id, subjectEnglishF1Id, subjectMathF2Id, subjectEnglishF2Id,
            sectionAMathId, sectionAEngId, sectionZMathId, sectionZEngId,
            studentMarks1AMathId, studentMarks1AEngId,
            auditoriumId;
    static MentorCard engMentor, mathMentor;

    @BeforeClass
    public static void fillData() throws ValidationException{
        Faculty faculty1 = university.createFaculty("Faculty1");
        faculty1Id = Helper.generateNewId();
        faculty1.setId(faculty1Id);

        Faculty faculty2 = university.createFaculty("Faculty2");
        faculty2Id = Helper.generateNewId();
        faculty2.setId(faculty2Id);

        Group groupA = faculty1.createGroup("GroupA");
        groupAId = Helper.generateNewId();
        groupA.setId(groupAId);

        Group groupB = faculty1.createGroup("GroupB");
        groupBId = Helper.generateNewId();
        groupB.setId(groupBId);

        Group groupC = faculty1.createGroup("GroupC");
        groupCId = Helper.generateNewId();
        groupC.setId(groupCId);

        Group groupY = faculty2.createGroup("GroupY");
        groupYId = Helper.generateNewId();
        groupY.setId(groupYId);

        Group groupZ = faculty2.createGroup("GroupZ");
        groupZId = Helper.generateNewId();
        groupZ.setId(groupZId);

//STUDENTS
        StudentCard studentTed = new StudentCard("Ted");
        StudentCard student1A = groupA.takeStudent(studentTed);
        student1AId = Helper.generateNewId();
        student1A.setId(student1AId);

        StudentCard studentBen = new StudentCard("Ben");
        StudentCard student2A = groupA.takeStudent(studentBen);
        student2AId = Helper.generateNewId();
        student2A.setId(student2AId);

        StudentCard studentAnn = new StudentCard("Ann");
        StudentCard student3A = groupA.takeStudent(studentAnn);
        student3AId = Helper.generateNewId();
        student3A.setId(student3AId);

        StudentCard studentMargaret = new StudentCard("Margaret");
        StudentCard student1Z = groupA.takeStudent(studentMargaret);
        student1ZId = Helper.generateNewId();
        student1Z.setId(student1ZId);

        Journal journalA = groupA.getJournal();
        journalAId = Helper.generateNewId();
        journalA.setId(journalAId);

        Journal journalZ = groupZ.getJournal();
        journalZId = Helper.generateNewId();
        journalZ.setId(journalZId);

        Subject subjectMathF1 = faculty1.addSubject("Math");
        subjectMathF1Id = Helper.generateNewId();
        subjectMathF1.setId(subjectMathF1Id);

        Subject subjectEnglishF1 = faculty1.addSubject("English");
        subjectEnglishF1Id = Helper.generateNewId();
        subjectEnglishF1.setId(subjectEnglishF1Id);

        Subject subjectMathF2 = faculty1.addSubject("Math");
        subjectMathF2Id = Helper.generateNewId();
        subjectMathF2.setId(subjectMathF2Id);

        Subject subjectEnglishF2 = faculty1.addSubject("English");
        subjectEnglishF2Id = Helper.generateNewId();
        subjectEnglishF2.setId(subjectEnglishF2Id);

///SECTIONS

        Section sectionAMath = journalA.createSection(subjectMathF1);
        sectionAMathId = Helper.generateNewId();
        sectionAMath.setId(sectionAMathId);

        Section sectionAEng = journalA.createSection(subjectEnglishF1);
        sectionAEngId = Helper.generateNewId();
        sectionAEng.setId(sectionAEngId);

        Section sectionZMath = journalZ.createSection(subjectMathF2);
        sectionZMathId = Helper.generateNewId();
        sectionZMath.setId(sectionZMathId);

        Section sectionZEng = journalZ.createSection(subjectEnglishF2);
        sectionZEngId = Helper.generateNewId();
        sectionZEng.setId(sectionZEngId);

//STUDENT MARKS

        StudentMarks studentMarks1AMath = sectionAMath.createStudentMarks(student1A);
        studentMarks1AMathId = Helper.generateNewId();
        studentMarks1AMath.setId(studentMarks1AMathId);

        StudentMarks studentMarks1AEng = sectionAEng.createStudentMarks(student1A);
        studentMarks1AEngId = Helper.generateNewId();
        studentMarks1AEng.setId(studentMarks1AEngId);

        StudentMarks studentMarks2AMath = sectionAMath.createStudentMarks(student2A);
        long studentMarks2AMathId = Helper.generateNewId();
        studentMarks2AMath.setId(studentMarks2AMathId);

        StudentMarks studentMarks2AEng = sectionAEng.createStudentMarks(student2A);
        long studentMarks2AEngId = Helper.generateNewId();
        studentMarks2AEng.setId(studentMarks2AEngId);

        StudentMarks studentMarks3AMath = sectionAMath.createStudentMarks(student3A);
        long studentMarks3AMathId = Helper.generateNewId();
        studentMarks3AMath.setId(studentMarks3AMathId);

        StudentMarks studentMarks3AEng = sectionAEng.createStudentMarks(student3A);
        long studentMarks3AEngId = Helper.generateNewId();
        studentMarks3AEng.setId(studentMarks3AEngId);

        StudentMarks studentMarks1ZMath = sectionZMath.createStudentMarks(student1Z);
        long studentMarks1ZMathId = Helper.generateNewId();
        studentMarks1ZMath.setId(studentMarks1ZMathId);

        StudentMarks studentMarks1ZEng = sectionZEng.createStudentMarks(student1Z);
        long studentMarks1ZEngId = Helper.generateNewId();
        studentMarks1ZEng.setId(studentMarks1ZEngId);


//MENTORS
        engMentor = faculty1.hireMentor("ENGLISH teacher");
        engMentorId = Helper.generateNewId();
        engMentor.setId(engMentorId);
        mathMentor = faculty1.hireMentor("MATH teacher");
        mathMentorId = Helper.generateNewId();
        mathMentor.setId(mathMentorId);

//MARKS

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

//AUDITORIUM
        Auditorium auditorium = faculty1.addAuditorium(111);
        auditoriumId = Helper.generateNewId();
        auditorium.setId(auditoriumId);
    }

}
