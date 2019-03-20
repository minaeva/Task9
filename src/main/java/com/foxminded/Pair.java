package com.foxminded;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalTime;

public class Pair {

    @Getter @Setter private long id;
    @Getter @Setter private long dayScheduleId;
    @Getter @Setter private LocalTime startTime;
    @Getter @Setter private Lesson lesson;

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
        //Lesson saved = lessonDAO.save(lesson);
        lesson.setId(IdGenerator.newId());
        return lesson;
    }

    public void removeLesson(long id) throws ValidationException{
        if (this.lesson.getId() != id)
            throw new ValidationException("Lesson with id " + id + " does't exist");
        this.lesson = null;
    }
}
