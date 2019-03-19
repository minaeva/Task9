package com.foxminded;

import java.util.List;
import java.util.UUID;

public class Journal {

    private UUID id;
    private UUID groupID;
    private UUID facultyID;
    private List<Section> sections;
    private List<StudentMarks> studentMarks;

    public Journal(){}

    public Journal(Group group){
        this.groupID = group.getId();
        this.facultyID = group.getFacultyID();
    }

    public Section createSection(Subject subject){
        Section section = new Section(subject);
        section.setJournalID(this.id);
        sections.add(section);
        return section;
    }

    public void removeSection(Section section){
        //todo
        sections.remove(section);
    }

    public double calculateAverageMark() {
        //todo
        return 1.0;
    }

    public void addMark(StudentCard studentCard, Subject subject, int mark){
        //todo
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getGroupID() {
        return groupID;
    }

    public void setGroupID(UUID groupID) {
        this.groupID = groupID;
    }

    public UUID getFacultyID() {
        return facultyID;
    }

    public void setFacultyID(UUID facultyID) {
        this.facultyID = facultyID;
    }
}
