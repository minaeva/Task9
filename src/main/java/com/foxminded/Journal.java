package com.foxminded;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

public class Journal {

    @Getter @Setter private long id;
    @Getter @Setter private long groupId;
    @Getter @Setter private long facultyId;
    @Getter @Setter private List<Section> sections;
    @Getter @Setter private List<StudentMarks> studentMarks;

    public Journal(){}

 /*   public Journal(Group group){
        this.groupId = group.getId();
        this.facultyId = group.getFacultyId();
    }
*/
    public Section createSection(Subject subject){
        Section section = new Section(subject);
    //    section.setJournalId(this.id);
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
}
