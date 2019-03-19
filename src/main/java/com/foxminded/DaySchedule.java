package com.foxminded;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class DaySchedule {

    private UUID id;
    private WorkDay workDay;
    private UUID scheduleID;
    private List<Pair> pairs;

    public DaySchedule(){}

    public DaySchedule(WorkDay workDay){
        this.workDay =  workDay;
    }

    public DaySchedule getDaySchedule(){
    return this;
    }

    public Pair createPair (LocalTime startTime) {
        Pair pair = new Pair(startTime);
        pair.setDayScheduleID(this.id);
        pairs.add(pair);
        return pair;
    }

    public void removePair (Pair pair) throws ValidationException{
        if (!pairs.contains(pair)) throw new ValidationException("Pair with id " + pair.getId() + " doesn't exist");
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public WorkDay getWorkDay() {
        return workDay;
    }

    public void setWorkDay(WorkDay workDay) {
        this.workDay = workDay;
    }

    public UUID getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(UUID scheduleID) {
        this.scheduleID = scheduleID;
    }
}

