package com.foxminded;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.Data;

@Data
public class DaySchedule {

        private long id;
        private WorkDay workDay;
        private long scheduleId;
        private List<Pair> pairs = new ArrayList<>();

    public DaySchedule(){}

    public DaySchedule(WorkDay workDay){
        this.workDay = workDay;
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

    public Pair findPair(long pairId) throws EntityNotFoundException{
        Predicate<Pair> p = pr -> pr.getId() == pairId;
        if (pairs.stream().noneMatch(p)) throw new EntityNotFoundException("Pair with id " + pairId + " doesn't exist");
        return pairs.stream().filter(p).collect(Collectors.toList()).get(0);
    }


    public void removePair (long pairId) throws EntityNotFoundException{
        Pair pair = findPair(pairId);
        pairs.remove(pair);
    }
}

