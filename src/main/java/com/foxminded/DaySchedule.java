package com.foxminded;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
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

    public Pair findPair(long pairId) throws EntityNotFoundException{
        Predicate<Pair> p = pr -> pr.getId() == pairId;
        return Helper.validateIfExists(pairs, p, "Pair",pairId );
    }

    public void removePair(long pairId) throws EntityNotFoundException{
        Pair pair = findPair(pairId);
        pairs.remove(pair);
    }
}