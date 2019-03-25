package com.foxminded;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScheduleTest extends FillingUniversityWithData {

    @Test
    public void createDaySchedule() throws EntityNotFoundException{
        Faculty faculty = university.findFaculty(faculty1Id);
        Schedule schedule = faculty.createSchedule();

        int beforeSize = schedule.getDaySchedules().size();
        schedule.createDaySchedule(WorkDay.MONDAY);
        schedule.createDaySchedule(WorkDay.THURSDAY);
        schedule.createDaySchedule(WorkDay.FRIDAY);
        int afterSize = schedule.getDaySchedules().size();

        assertEquals(beforeSize + 3, afterSize);
    }


    @Test
    public void removeDaySchedule() throws EntityNotFoundException{
        Faculty faculty = university.findFaculty(faculty1Id);
        Schedule schedule = faculty.createSchedule();
        DaySchedule daySchedule1 = schedule.createDaySchedule(WorkDay.MONDAY);
        long schedule1Id = Helper.generateNewId();
        daySchedule1.setId(schedule1Id);

        DaySchedule daySchedule2 = schedule.createDaySchedule(WorkDay.THURSDAY);
        long schedule2Id = Helper.generateNewId();
        daySchedule2.setId(schedule2Id);

        schedule.createDaySchedule(WorkDay.FRIDAY);

        int beforeSize = schedule.getDaySchedules().size();
        schedule.removeDaySchedule(schedule1Id);
        schedule.removeDaySchedule(schedule2Id);
        int afterSize = schedule.getDaySchedules().size();

        assertEquals(beforeSize - 2, afterSize);
    }
}
