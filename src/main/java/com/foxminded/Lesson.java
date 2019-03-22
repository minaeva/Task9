package com.foxminded;

import lombok.Data;

@Data
public class Lesson {

    private long id;
    private long pairId;
    private Group group;
    private Subject subject;
    private MentorCard mentorCard;
    private Auditorium auditorium;
}
