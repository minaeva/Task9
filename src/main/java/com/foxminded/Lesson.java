package com.foxminded;

import java.util.UUID;

public class Lesson {

    private UUID id;
    private UUID pairID;
    private Group group;
    private Subject subject;
    private MentorCard mentorCard;
    private Auditorium auditorium;

    public Lesson(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPairID() {
        return pairID;
    }

    public void setPairID(UUID pairID) {
        this.pairID = pairID;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public MentorCard getMentorCard() {
        return mentorCard;
    }

    public void setMentorCard(MentorCard mentorCard) {
        this.mentorCard = mentorCard;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }
}
