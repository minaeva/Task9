package com.foxminded;

import org.junit.Test;

import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class PairTest extends FillingUniversityWithData {

    @Test
    public void createLesson() throws EntityNotFoundException{
        Faculty faculty = university.findFaculty(faculty1Id);
        Group group = faculty.findGroup(groupAId);
        Subject subject = faculty.findSubject(subjectMathF1Id);

        Auditorium auditorium = faculty.findAuditorium(auditoriumId);
        MentorCard mentor = faculty.findMentor(mathMentorId);

        Schedule schedule = faculty.createSchedule();
        DaySchedule daySchedule = schedule.createDaySchedule(WorkDay.MONDAY);
        Pair pair = daySchedule.createPair(LocalTime.of(8, 30));

        pair.createLesson(group, subject, mentor, auditorium);
        Lesson foundLesson = pair.getLesson();

        assertEquals("Math", foundLesson.getSubject().getName());
    }


//    @Test
    public void removePair() throws EntityNotFoundException{
        Faculty faculty = university.findFaculty(faculty1Id);
        Schedule schedule = faculty.createSchedule();

        DaySchedule daySchedule = schedule.createDaySchedule(WorkDay.MONDAY);
        Pair pair = daySchedule.createPair(LocalTime.of(10, 10));
        long pairId = Helper.generateNewId();
        pair.setId(pairId);

        int beforeSize = daySchedule.getPairs().size();
        daySchedule.removePair(pairId);
        int afterSize = daySchedule.getPairs().size();

        assertEquals(beforeSize - 1, afterSize);
    }}
