package com.foxminded;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalTime;
import static org.junit.Assert.assertEquals;

public class DayScheduleTest {
    private University university;
    private Faculty faculty;
    private Schedule schedule;
    private DaySchedule daySchedule;

    @Before
    public void before(){
        university = new University();
        faculty = university.createFaculty("FACULTY");
        schedule = faculty.createSchedule();
        daySchedule = schedule.createDaySchedule(WorkDay.MONDAY);
    }

    @Test
    public void createPair(){
        daySchedule.createPair(LocalTime.of(10, 10));
        assertEquals(1, daySchedule.getPairs().size());
    }

   @Test
    public void removePair(){
       daySchedule.createPair(LocalTime.of(10, 10));

       int beforeSize = daySchedule.getPairs().size();
       daySchedule.removePair(LocalTime.of(10,10));
       int afterSize = daySchedule.getPairs().size();
       assertEquals(beforeSize - 1, afterSize);
    }
}
