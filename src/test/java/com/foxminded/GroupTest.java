package com.foxminded;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class GroupTest {

    @Test
    public void findStudents_correct(){
        String student1 = "Jinny";
        String student2 = "Barbara";
        String student3 = "Serge";
        String student4 = "Olga";
        String student5 = "Helen";
        String student6 = "Ann";
        String student7 = "Maria";

        List<StudentCard> expected = new ArrayList<>();
        expected.add(new StudentCard(student1));
        expected.add(new StudentCard(student2));
        expected.add(new StudentCard(student3));
        expected.add(new StudentCard(student4));
        expected.add(new StudentCard(student5));
        expected.add(new StudentCard(student6));
        expected.add(new StudentCard(student7));

        System.out.println("****EXPECTED");
        for (StudentCard s: expected
             ) {
            System.out.println(s.getName());
        }

        Faculty faculty = new Faculty("IASA");
        StudentCard studentCard1 = faculty.takeStudent(student1);
        StudentCard studentCard2 = faculty.takeStudent(student2);
        StudentCard studentCard3 = faculty.takeStudent(student3);
        StudentCard studentCard4 = faculty.takeStudent(student4);
        StudentCard studentCard5 = faculty.takeStudent(student5);
        StudentCard studentCard6 = faculty.takeStudent(student6);
        StudentCard studentCard7 = faculty.takeStudent(student7);
        Group group = new Group("KA-11");
        group.takeStudent(studentCard1);
        group.takeStudent(studentCard2);
        group.takeStudent(studentCard3);
        group.takeStudent(studentCard4);
        group.takeStudent(studentCard5);
        group.takeStudent(studentCard6);
        group.takeStudent(studentCard7);
        ArrayList<StudentCard> actual = group.findStudents();

        System.out.println("****ACTUAL");
        for (StudentCard s: actual
        ) {
            System.out.println(s.getName());
        }

//        Assert.assertTrue(expected.containsAll(actual));
    }

}
