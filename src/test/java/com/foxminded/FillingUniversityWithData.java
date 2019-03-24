package com.foxminded;

import org.junit.BeforeClass;

public class FillingUniversityWithData {
    static final protected University university =  new University();

    @BeforeClass
    private void fillData() throws ValidationException, EntityNotFoundException{
        Faculty faculty1 = university.createFaculty("Faculty1");
        long faculty1Id = Helper.generateNewId();
        faculty1.setId(faculty1Id);

        Faculty faculty2 = university.createFaculty("Faculty2");
        long faculty2Id = Helper.generateNewId();
        faculty2.setId(faculty2Id);

        Group groupA = faculty1.createGroup("GroupA");
        long groupAId = Helper.generateNewId();
        groupA.setId(groupAId);

        Group groupB = faculty1.createGroup("GroupB");
        long groupBId = Helper.generateNewId();
        groupB.setId(groupBId);

        Group groupC = faculty1.createGroup("GroupC");
        long groupCId = Helper.generateNewId();
        groupC.setId(groupCId);

        Group groupY = faculty2.createGroup("GroupY");
        long groupYId = Helper.generateNewId();
        groupY.setId(groupYId);

        Group groupZ = faculty2.createGroup("GroupZ");
        long groupZId = Helper.generateNewId();
        groupZ.setId(groupZId);

//STUDENTS
        StudentCard studentTed = new StudentCard("Ted");
        StudentCard student1A = groupA.takeStudent(studentTed);
        long student1AId = Helper.generateNewId();
        student1A.setId(student1AId);

        StudentCard studentBen = new StudentCard("Ben");
        StudentCard student2A = groupA.takeStudent(studentBen);
        long student2AId = Helper.generateNewId();
        student2A.setId(student2AId);

        StudentCard studentAnn = new StudentCard("Ann");
        StudentCard student3A = groupA.takeStudent(studentAnn);
        long student3AId = Helper.generateNewId();
        student3A.setId(student3AId);

        StudentCard studentMargaret = new StudentCard("Margaret");
        StudentCard student1Z = groupA.takeStudent(studentMargaret);
        long student1ZId = Helper.generateNewId();
        student1Z.setId(student1ZId);

        Journal journalA = groupA.getJournal();
        long journalAId = Helper.generateNewId();
        journalA.setId(journalAId);

        Journal journalZ = groupZ.getJournal();
        long journalZId = Helper.generateNewId();
        journalZ.setId(journalZId);

        Subject subjectMath = faculty1.addSubject("Math");
        long mathId = Helper.generateNewId();
        subjectMath.setId(mathId);

        Subject subjectEnglish = faculty1.addSubject("English");
        long englishId = Helper.generateNewId();
        subjectEnglish.setId(englishId);

        Section sectionAMath = journalA.createSection(subjectMath);
        long sectionAMathId = Helper.generateNewId();
        sectionAMath.setId(sectionAMathId);

        Section sectionAEng = journalA.createSection(subjectEnglish);
        long sectionAEngId = Helper.generateNewId();
        sectionAEng.setId(sectionAEngId);

        Section sectionZMath = journalZ.createSection(subjectMath);
        long sectionZMathId = Helper.generateNewId();
        sectionZMath.setId(sectionZMathId);

        Section sectionZEng = journalZ.createSection(subjectEnglish);
        long sectionZEngId = Helper.generateNewId();
        sectionZEng.setId(sectionZEngId);

//STUDENT MARKS

        StudentMarks studentMarks1AMath = sectionAMath.createStudentMarks(student1A);
        long studentMarks1AMathId = Helper.generateNewId();
        studentMarks1AMath.setId(studentMarks1AMathId);

        StudentMarks studentMarks1AEng = sectionAEng.createStudentMarks(student1A);
        long studentMarks1AEngId = Helper.generateNewId();
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

        StudentMarks studentMarks1ZMath = sectionAMath.createStudentMarks(student2A);
        long studentMarks1ZMathId = Helper.generateNewId();
        studentMarks1ZMath.setId(studentMarks1ZMathId);

        StudentMarks studentMarks1ZEng = sectionAEng.createStudentMarks(student1Z);
        long studentMarks1ZEngId = Helper.generateNewId();
        studentMarks1ZEng.setId(studentMarks1ZEngId);

    }
}
