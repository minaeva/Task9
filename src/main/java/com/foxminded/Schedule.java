package com.foxminded;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import static com.foxminded.Validator.*;

@Data
public class Schedule {

    private List<DaySchedule> daySchedules = new ArrayList<>();

    public DaySchedule createDaySchedule(WorkDay workDay){
        DaySchedule daySchedule = new DaySchedule(workDay);
        daySchedules.add(daySchedule);
        return daySchedule;
    }

    public boolean removeDaySchedule(WorkDay day) throws IllegalArgumentException{
        return daySchedules.removeIf(daySchedule -> daySchedule.getWorkDay().equals(day));
    }

    public DaySchedule findDaySchedule(WorkDay day) throws IllegalArgumentException{
        return findObjectByWorkdayIfExists(daySchedules,
                daySchedule -> daySchedule.getWorkDay().equals(day),
                "Day schedule",
                day);
    }
}
