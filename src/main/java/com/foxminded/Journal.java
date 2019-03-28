package com.foxminded;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import static com.foxminded.Validator.*;


@Data
public class Journal {

    private List<Section> sections = new ArrayList<>();

    public Section createSection(String subjectName){
        Section section = new Section(subjectName);
        sections.add(section);
        return section;
    }

    public Section findSection(String sectionName){
        return findObjectByNameIfExists(sections,
                section -> section.getSectionName().equals(sectionName),
                "Section",
                sectionName);
    }

    public boolean removeSection(String sectionName){
        return sections.removeIf(section -> section.getSectionName().equals(sectionName));
    }

    public double calculateAverageMark() {
        if (sections.size() == 0) {
            return 0;
        }
        double result = 0.0;
        int counter = 0;
        for (Section section: sections) {
            double midAverage = section.calculateAverageMark();
            if (midAverage != 0){
                result += midAverage;
                counter++;
            }
        }
        return (result == 0) ? 0 : result/counter;
    }

    public void addMark(String studentName, String subjectName, int mark){
        Section section = findSection(subjectName);
        section.addMark(studentName, mark);
    }
}
