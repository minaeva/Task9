package com.foxminded;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class Schedule {

    private long id;
    private List<DaySchedule> daySchedules = new ArrayList<>();
    private long facultyId;

    public DaySchedule createDaySchedule(WorkDay workDay){
        DaySchedule daySchedule = new DaySchedule(workDay);
         daySchedules.add(daySchedule);
        return daySchedule;
    }

    public boolean removeDaySchedule(long dayScheduleId) throws IllegalArgumentException{
        return daySchedules.removeIf(daySchedule1 -> daySchedule1.getId() == dayScheduleId);
    }

    public DaySchedule findDaySchedule(long dayScheduleId) throws IllegalArgumentException{
        return Helper.validateObjectExists(daySchedules, daySchedule -> daySchedule.getId() == dayScheduleId, "Day schedule", dayScheduleId);
    }
}
