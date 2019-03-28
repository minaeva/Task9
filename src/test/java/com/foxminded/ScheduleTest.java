package com.foxminded;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ScheduleTest {
    private University university;
    private Faculty faculty;
    private Schedule schedule;

    @Before
    public void before(){
        university = new University();
        faculty = university.createFaculty("FACULTY");
        schedule = faculty.createSchedule();
    }

    @Test
    public void createDaySchedule(){
        schedule.createDaySchedule(WorkDay.MONDAY);
        schedule.createDaySchedule(WorkDay.TUESDAY);
        schedule.createDaySchedule(WorkDay.FRIDAY);

        assertEquals(3, schedule.getDaySchedules().size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void removeDaySchedule() {
        schedule.createDaySchedule(WorkDay.THURSDAY);

        schedule.removeDaySchedule(WorkDay.THURSDAY);

        schedule.findDaySchedule(WorkDay.THURSDAY);
    }
}
