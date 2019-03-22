package com.foxminded;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
public class Journal {

    private long id;
    private long groupId;
    private long facultyId;
    private List<Section> sections = new ArrayList<>();
    private List<StudentMarks> studentMarks = new ArrayList<>();

    public Journal(){}

    public Section createSection(Subject subject){
        Section section = new Section(subject);
    //    section.setJournalId(this.id);
        sections.add(section);
        return section;
    }

    public Section findSection(long sectionId) throws EntityNotFoundException{
        Predicate<Section> p = s -> s.getId() == sectionId;
        if (sections.stream().noneMatch(p)) throw new EntityNotFoundException("Section with id " + sectionId + " doesn't exist");
        return sections.stream().filter(p).collect(Collectors.toList()).get(0);
    }

    public void removeSection(long sectionId) throws EntityNotFoundException{
        Section section = findSection(sectionId);
        sections.remove(section);
    }

    public void dismantle(){
        sections = null;
    }

    public double calculateAverageMark() {
        if (sections.size() == 0) return 0;
        double result = 0.0;
        int counter = 0;
        for (Section section: sections) {
            result += section.calculateAverageMark();
            counter++;
        }
        return result/counter;
    }

    public void addMark(StudentCard studentCard, Subject subject, int mark){
        //todo
    }
}
