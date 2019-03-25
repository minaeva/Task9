package com.foxminded;

import org.junit.Test;
import java.time.LocalTime;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class PairTest extends FillingUniversityWithData {

    @Test
    public void createLesson(){
        Schedule schedule = faculty1.createSchedule();
        DaySchedule daySchedule = schedule.createDaySchedule(WorkDay.MONDAY);
        Pair pair = daySchedule.createPair(LocalTime.of(8, 30));

        pair.createLesson(groupA, subjectEnglishF1, engMentor, auditorium);
        Lesson foundLesson = pair.getLesson();
        assertEquals("English", foundLesson.getSubject().getName());
    }

    @Test
    public void removeLesson() throws IllegalArgumentException{
        Schedule schedule = faculty1.createSchedule();
        DaySchedule daySchedule = schedule.createDaySchedule(WorkDay.TUESDAY);
        Pair pair = daySchedule.createPair(LocalTime.of(8, 30));

        pair.createLesson(groupA, subjectMathF1, mathMentor, auditorium);
        Lesson foundLesson = pair.getLesson();
        long lessonId = Helper.generateNewId();
        foundLesson.setId(lessonId);

        pair.removeLesson(lessonId);
        assertNull(pair.getLesson());
    }
}
