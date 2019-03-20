package com.foxminded;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

public class Schedule {

    @Getter @Setter private long id;
    @Getter @Setter private List<DaySchedule> daySchedules;
    @Getter @Setter private long facultyId;

    public DaySchedule createDaySchedule(WorkDay workDay){
        DaySchedule daySchedule = new DaySchedule(workDay);
        //daySchedule.setScheduleId(this.id);
        daySchedules.add(daySchedule);
        return daySchedule;
    }

    public void removeDaySchedule(DaySchedule daySchedule){
        //todo
        daySchedules.remove(daySchedule);
    }
}
