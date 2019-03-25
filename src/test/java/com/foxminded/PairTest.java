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

    @Test
    public void removePair() throws EntityNotFoundException, ValidationException{
        Faculty faculty = university.findFaculty(faculty1Id);
        Group group = faculty.findGroup(groupAId);
        Subject subject = faculty.findSubject(subjectMathF1Id);

        Auditorium auditorium = faculty.findAuditorium(auditoriumId);
        MentorCard mentor = faculty.findMentor(mathMentorId);

        Schedule schedule = faculty.createSchedule();
        DaySchedule daySchedule = schedule.createDaySchedule(WorkDay.TUESDAY);
        Pair pair = daySchedule.createPair(LocalTime.of(8, 30));

        pair.createLesson(group, subject, mentor, auditorium);
        Lesson foundLesson = pair.getLesson();
        long lessonId = Helper.generateNewId();
        foundLesson.setId(lessonId);

        pair.removeLesson(lessonId);
        assertEquals(null, pair.getLesson());
    }
}
