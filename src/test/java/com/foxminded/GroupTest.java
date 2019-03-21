package com.foxminded;

import static org.junit.Assert.*;
import org.junit.Test;
import java.util.List;

public class GroupTest {

    @Test
    public void takeStudent() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        StudentCard student = faculty.takeStudent("John");
        Group group = faculty.createGroup("Group");
        StudentCard takenStudent = group.takeStudent(student);

        long id = IdGenerator.newId();
        takenStudent.setId(id);
        StudentCard foundStudent = group.findStudent(id);
        assertEquals(takenStudent, foundStudent);
    }

    @Test
    public void findStudent() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        Group group = faculty.createGroup("Group");
        StudentCard student = faculty.takeStudent("Madonna");
        StudentCard takenStudent = group.takeStudent(student);
        long studentId = IdGenerator.newId();
        takenStudent.setId(studentId);

        StudentCard foundStudent = faculty.findStudent(studentId);
        assertEquals(takenStudent, foundStudent);
    }

    @Test
    public void dismissStudent() throws ValidationException, EntityNotFoundException{
        University university = new University();
        Faculty faculty = university.createFaculty("Faculty");
        Group group = faculty.createGroup("Group");
        StudentCard student = faculty.takeStudent("Bach");
        StudentCard takenStudent =  group.takeStudent(student);
        int size = group.findStudents().size();
        assertEquals(1, size);

        long studentId = IdGenerator.newId();
        takenStudent.setId(studentId);
        group.dismissStudent(studentId);
        size = group.findStudents().size();
        assertEquals(0, size);
    }

    private boolean areListsEqual(List<StudentCard> first, List<StudentCard> second) {
        if (first.size() != second.size()) return false;
        int equalCounter = 0;
        for (StudentCard s1: first) {
            for (StudentCard s2: second){
                if ((s1.getName().equals(s2.getName())) && (s1.getId() == s2.getId()))
                    equalCounter++;
            }
        }
        return equalCounter == first.size();
    }


}
