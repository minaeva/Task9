package com.foxminded;

import org.junit.Test;
import java.time.LocalTime;
import static org.junit.Assert.assertEquals;

public class DayScheduleTest extends FillingUniversityWithData {

    @Test
    public void createPair() throws EntityNotFoundException{
        Faculty faculty = university.findFaculty(faculty1Id);
        Schedule schedule = faculty.createSchedule();
        DaySchedule daySchedule = schedule.createDaySchedule(WorkDay.MONDAY);

        int beforeSize = daySchedule.getPairs().size();
        daySchedule.createPair(LocalTime.of(10, 10));
        int afterSize = daySchedule.getPairs().size();
        assertEquals(beforeSize + 1, afterSize);
    }

   @Test
    public void removePair() throws EntityNotFoundException{
       Faculty faculty = university.findFaculty(faculty1Id);
       Schedule schedule = faculty.createSchedule();
       DaySchedule daySchedule = schedule.createDaySchedule(WorkDay.MONDAY);
       Pair pair = daySchedule.createPair(LocalTime.of(10, 10));
       long pairId = Helper.generateNewId();
       pair.setId(pairId);

       int beforeSize = daySchedule.getPairs().size();
       daySchedule.removePair(pairId);
       int afterSize = daySchedule.getPairs().size();
       assertEquals(beforeSize - 1, afterSize);
    }
}
