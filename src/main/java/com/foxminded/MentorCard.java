package com.foxminded;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
public class MentorCard {

    private long id;
    private String name;
    private long subjectId;
    private long facultyId;
    private List<Journal> journals = new ArrayList<>();

    public MentorCard(){}

    public MentorCard(String name){
        this.name = name;
    }

    public void fire(){
        System.out.println("Permission received. Mentor " + this.name + " can now be fired");
    }

    public void addMark(StudentCard studentCard, Subject subject, int mark) throws EntityNotFoundException{
        long groupId = studentCard.getGroupId();
        Journal journal = findJournal(groupId);
        journal.addMark(studentCard, subject, mark);
    }

    public Journal findJournal(long groupId) throws EntityNotFoundException{
        Predicate<Journal> p = j -> j.getGroupId() == groupId;
        if (journals.stream().noneMatch(p)) throw new EntityNotFoundException("Journal for group with id " + groupId + " doesn't exist");
        return journals.stream().filter(p).collect(Collectors.toList()).get(0);
    }

}