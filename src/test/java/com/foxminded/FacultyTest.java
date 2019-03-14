package com.foxminded;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class FacultyTest {

    @Test
    public void addStudent_Ivan_correct(){
        String actual = "Ivan";
        Faculty faculty = new Faculty();
        StudentCard studentCard = faculty.takeStudent(actual);
        System.out.println("Name " + studentCard.getName() + ", id " + studentCard.getID());
        Assert.assertEquals(studentCard.getName(), actual);
    }

    @Test
    public void addStudent_IvanIvan_null(){
        String actual = "Ivan";
        Faculty faculty = new Faculty();
        faculty.takeStudent(actual);
        StudentCard studentCard = faculty.takeStudent(actual);
        Assert.assertEquals(studentCard, null);
    }

    @Test
    public void addStudent_Sergey_correct(){
        String actual = "Sergey";
        Faculty faculty = new Faculty();
        StudentCard studentCard = faculty.takeStudent(actual);
        System.out.println("Name " + studentCard.getName() + ", id " + studentCard.getID());
        Assert.assertEquals(studentCard.getName(), actual);
    }

    @Test
    public void addStudent_Oleg_correct(){
        String actual = "Oleg";
        Faculty faculty = new Faculty();
        StudentCard studentCard = faculty.takeStudent(actual);
        System.out.println("Name " + studentCard.getName() + ", id " + studentCard.getID());
        Assert.assertEquals(studentCard.getName(), actual);
    }

    @Test
    public void addStudent_Mack_correct(){
        String actual = "Mack";
        Faculty faculty = new Faculty();
        StudentCard studentCard = faculty.takeStudent(actual);
        System.out.println("Name " + studentCard.getName() + ", id " + studentCard.getID());
        Assert.assertEquals(studentCard.getName(), actual);
    }

    @Test
    public void addStudent_Zack_correct(){
        String actual = "Zack";
        Faculty faculty = new Faculty();
        StudentCard studentCard = faculty.takeStudent(actual);
        System.out.println("Name " + studentCard.getName() + ", id " + studentCard.getID());
        Assert.assertEquals(studentCard.getName(), actual);
    }

    @Test
    public void addStudent_Luke_correct(){
        String actual = "Luke";
        Faculty faculty = new Faculty();
        StudentCard studentCard = faculty.takeStudent(actual);
        System.out.println("Name " + studentCard.getName() + ", id " + studentCard.getID());
        Assert.assertEquals(studentCard.getName(), actual);
    }

    @Test
    public void addStudent_John_correct(){
        String actual = "John";
        Faculty faculty = new Faculty();
        StudentCard studentCard = faculty.takeStudent(actual);
        System.out.println("Name " + studentCard.getName() + ", id " + studentCard.getID());
        Assert.assertEquals(studentCard.getName(), actual);
    }

    @Test
    public void findStudents_correct(){
        String student1 = "Jinny";
        String student2 = "Barbara";
        String student3 = "Serge";
        String student4 = "Olga";
        String student5 = "Helen";
        String student6 = "Ann";
        String student7 = "Maria";

        Set<StudentCard> expected = new HashSet<>();
        expected.add(new StudentCard(student1));
        expected.add(new StudentCard(student2));
        expected.add(new StudentCard(student3));
        expected.add(new StudentCard(student4));
        expected.add(new StudentCard(student5));
        expected.add(new StudentCard(student6));
        expected.add(new StudentCard(student7));

        Faculty faculty = new Faculty();
        faculty.takeStudent(student1);
        faculty.takeStudent(student2);
        faculty.takeStudent(student3);
        faculty.takeStudent(student4);
        faculty.takeStudent(student5);
        faculty.takeStudent(student6);
        faculty.takeStudent(student7);
        Set<StudentCard> actual = faculty.findStudents();

        Assert.assertTrue(doSetsContainSameNames(expected, actual));
    }

    private boolean doSetsContainSameNames(Set<StudentCard> set1, Set<StudentCard> set2){
        int equalsFound = 0;
        for (StudentCard expectedStudentCard : set1) {
            String expectedName = expectedStudentCard.getName();
            for (StudentCard actualStudentCard : set2) {
                if (actualStudentCard.getName() == expectedName)
                    equalsFound++;
            }
        }
        if (equalsFound == set1.size()) return true;
        return false;
    }
}
