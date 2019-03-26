package com.foxminded;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class DaySchedule {

    private long id;
    private WorkDay workDay;
    private long scheduleId;
    private List<Pair> pairs = new ArrayList<>();

    public DaySchedule(WorkDay workDay){
        this.workDay = workDay;
    }

    public DaySchedule getDaySchedule(){
    return this;
    }

    public Pair createPair(LocalTime startTime) {
        Pair pair = new Pair(startTime);
        pairs.add(pair);
        return pair;
    }

    public Pair findPair(long pairId) throws IllegalArgumentException{
        return Helper.validateObjectExists(pairs, pair1 -> pair1.getId() == pairId, "Pair",pairId );
    }

    public boolean removePair(long pairId) throws IllegalArgumentException{
        return pairs.removeIf(pair1 -> pair1.getId() == pairId);
    }
}