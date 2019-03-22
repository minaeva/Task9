package com.foxminded;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
public class Schedule {

    private long id;
    private List<DaySchedule> daySchedules = new ArrayList<>();
    private long facultyId;

    public DaySchedule createDaySchedule(WorkDay workDay){
        DaySchedule daySchedule = new DaySchedule(workDay);
        //daySchedule.setScheduleId(this.id);
        daySchedules.add(daySchedule);
        return daySchedule;
    }

    public void removeDaySchedule(long dayScheduleId) throws EntityNotFoundException{
        DaySchedule daySchedule = findDaySchedule(dayScheduleId);
        daySchedules.remove(daySchedule);
    }

    public DaySchedule findDaySchedule(long dayScheduleId) throws EntityNotFoundException{
        Predicate<DaySchedule> p = d -> d.getId() == dayScheduleId;
        if (daySchedules.stream().noneMatch(p)) throw new EntityNotFoundException("Day schedule with id " + dayScheduleId + " doesn't exist");
        return daySchedules.stream().filter(p).collect(Collectors.toList()).get(0);
    }
}
