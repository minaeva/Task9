package com.foxminded;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalTime;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PairTest {

    private Pair pair;

    @Before
    public void before(){
        pair = new Pair(LocalTime.of(8, 30));
    }

    @Test
    public void createLesson(){
        pair.createLesson("GROUP", "English", "MENTOR", 555);

        Lesson foundLesson = pair.getLesson();
        assertEquals("English", foundLesson.getSubjectName());
    }

    @Test
    public void removeLesson(){
        pair.createLesson("GROUP2", "German", "MENTOR2", 444);

        pair.removeLesson();

        assertNull(pair.getLesson());
    }
}