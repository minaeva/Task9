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

    public Section findSection(long sectionId) throws IllegalArgumentException{
        return Helper.validateObjectExists(sections, section -> section.getId() == sectionId, "Section", sectionId);
    }

    public Section findSection(Subject subject) throws IllegalArgumentException{
        return Helper.validateObjectExists(sections, section -> section.getSubject().equals(subject), "Section", subject.getId());
    }

    public boolean removeSection(long sectionId) throws IllegalArgumentException{
        return sections.removeIf(section1 -> section1.getId() == sectionId);
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

    public void addMark(StudentCard studentCard, Subject subject, int mark) throws IllegalArgumentException{
        Section section = findSection(subject);
        section.addMark(studentCard, mark);
    }
}
