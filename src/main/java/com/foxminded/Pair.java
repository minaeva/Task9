package com.foxminded;

import lombok.Data;
import java.time.LocalTime;

@Data
public class Pair {

    private long id;
    private long dayScheduleId;
    private LocalTime startTime;
    private Lesson lesson;
    private final int DURATION = 90;

    public Pair(LocalTime startTime){
        this.startTime = startTime;
    }

    public Lesson createLesson(Group group, Subject subject, MentorCard mentorCard, Auditorium auditorium){
        this.lesson = new Lesson();
        lesson.setGroup(group);
        lesson.setSubject(subject);
        lesson.setMentorCard(mentorCard);
        lesson.setAuditorium(auditorium);
        return lesson;
    }

    public void removeLesson(long id) throws ValidationException{
        if (this.lesson.getId() != id)
            throw new ValidationException("Lesson with id " + id + " does't exist");
        this.lesson = null;
    }
}
