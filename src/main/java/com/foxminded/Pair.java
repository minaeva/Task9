package com.foxminded;

import lombok.Data;
import java.time.LocalTime;

@Data
public class Pair {

    private LocalTime startTime;
    private Lesson lesson;
    private final int DURATION = 90;

    public Pair(LocalTime startTime){
        this.startTime = startTime;
    }

    public Lesson createLesson(String groupName, String subjectName,
                               String mentorName, int auditoriumNumber){
        this.lesson = new Lesson();
        lesson.setGroupName(groupName);
        lesson.setSubjectName(subjectName);
        lesson.setMentorName(mentorName);
        lesson.setAuditoriumNumber(auditoriumNumber);
        return lesson;
    }

    public void removeLesson() {
        this.lesson = null;
    }
}
