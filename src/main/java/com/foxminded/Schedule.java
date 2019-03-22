package com.foxminded;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

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
        validateIfExists(daySchedules, p, "Day schedule",dayScheduleId );
        return daySchedules.stream().filter(p).findFirst().get();
    }

    private <T> void validateIfExists(List<T> list, Predicate<T> predicate, String objectName, long id) throws EntityNotFoundException{
        if (list.stream().noneMatch(predicate))
            throw new EntityNotFoundException(objectName + " with id " + id + " doesn't exist");
    }
}
