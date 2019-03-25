package com.foxminded;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Data
public class Journal {

    private long id;
    private long groupId;
    private long facultyId;
    private List<Section> sections = new ArrayList<>();

    public Journal(){}

    public Section createSection(Subject subject){
        Section section = new Section(subject);
        sections.add(section);
        return section;
    }

    public Section findSection(long sectionId) throws EntityNotFoundException{
        Predicate<Section> p = section -> section.getId() == sectionId;
        Helper.validateIfExists(sections, p, "Section", sectionId);
        return sections.stream().filter(p).findFirst().get();
    }

    public Section findSection(Subject subject) throws EntityNotFoundException{
        Predicate<Section> p = s -> s.getSubject() == subject;
        Helper.validateIfExists(sections, p, "Subject", subject.getId());
        return sections.stream().filter(p).findFirst().get();
    }

    public void removeSection(long sectionId) throws EntityNotFoundException{
        Section section = findSection(sectionId);
        sections.remove(section);
    }

    public double calculateAverageMark() {
        if (sections.size() == 0) return 0;
        double result = 0.0;
        int counter = 0;
        for (Section section: sections) {
            double midAverage = section.calculateAverageMark();
            if (midAverage != 0){
                result += midAverage;
                counter++;
            }
        }
        if (result == 0) return 0;
        return result/counter;
    }

    public void addMark(StudentCard studentCard, Subject subject, int mark) throws EntityNotFoundException, ValidationException{
        Section section = findSection(subject);
        section.addMark(studentCard, mark);
    }
}
