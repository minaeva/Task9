package com.foxminded;

import java.time.LocalTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

    public class DaySchedule {

        @Getter @Setter private long id;
        @Getter @Setter private WorkDay workDay;
        @Getter @Setter private long scheduleId;
        @Getter @Setter private List<Pair> pairs;

    public DaySchedule(){}

    public DaySchedule(WorkDay workDay){
        this.workDay =  workDay;
    }

    public DaySchedule getDaySchedule(){
    return this;
    }

    public Pair createPair (LocalTime startTime) {
        Pair pair = new Pair(startTime);
        //pair.setDayScheduleId(this.id);
        pairs.add(pair);
        return pair;
    }

    public void removePair (Pair pair) throws ValidationException{
        if (!pairs.contains(pair)) throw new ValidationException("Pair with id " + pair.getId() + " doesn't exist");
    }
    }

