package com.foxminded;

import java.time.LocalTime;
import java.util.UUID;

public class Pair {

    private UUID id;
    private UUID dayScheduleID;
    private LocalTime startTime;
    private Lesson lesson;

    public Pair(){}

    public Pair(LocalTime startTime){
        this.startTime = startTime;
    }

    public Lesson createlesson(Group group, Subject subject, MentorCard mentorCard, Auditorium auditorium){
        this.lesson = new Lesson();
        lesson.setGroup(group);
        lesson.setSubject(subject);
        lesson.setMentorCard(mentorCard);
        lesson.setAuditorium(auditorium);
        //Lesson saved = lessonDAO.save(lesson); lesson.setID(saved.getID());
        return lesson;
    }

    public void removeLesson(UUID id) throws ValidationException{
        if (this.lesson.getId() != id)
            throw new ValidationException("Lesson with id " + id + " does't exist");
        this.lesson = null;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getDayScheduleID() {
        return dayScheduleID;
    }

    public void setDayScheduleID(UUID dayScheduleID) {
        this.dayScheduleID = dayScheduleID;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
