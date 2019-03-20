package com.foxminded;

import lombok.Getter;
import lombok.Setter;

public class Lesson {

    @Getter @Setter private long id;
    @Getter @Setter private long pairId;
    @Getter @Setter private Group group;
    @Getter @Setter private Subject subject;
    @Getter @Setter private MentorCard mentorCard;
    @Getter @Setter private Auditorium auditorium;

    public Lesson(){}

}
