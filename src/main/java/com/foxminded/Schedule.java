package com.foxminded;

import java.util.List;
import java.util.UUID;

public class Schedule {

    private UUID id;
    private List<DaySchedule> daySchedules;
    private UUID facultyID;

    public DaySchedule createDaySchedule(WorkDay workDay){
        DaySchedule daySchedule = new DaySchedule(workDay);
        daySchedule.setScheduleID(this.id);
        daySchedules.add(daySchedule);
        return daySchedule;
    }

    public void removeDaySchedule(DaySchedule daySchedule){
        //todo
        daySchedules.remove(daySchedule);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(UUID facultyID) {
        this.facultyID = facultyID;
    }
}
