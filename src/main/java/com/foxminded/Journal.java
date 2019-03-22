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
    private List<StudentMarks> studentMarks = new ArrayList<>();

    public Journal(){}

    public Section createSection(Subject subject){
        Section section = new Section(subject);
    //?    section.setJournalId(this.id);
        sections.add(section);
        return section;
    }

    public Section findSection(long sectionId) throws EntityNotFoundException{
        Predicate<Section> p = section -> section.getId() == sectionId;
        validateIfExists(sections, p, "Section", sectionId);
        return sections.stream().filter(p).findFirst().get();
    }

    public Section findSection(Subject subject) throws EntityNotFoundException{
        Predicate<Section> p = s -> s.getSubject() == subject;
        validateIfExists(sections, p, "Subject", subject.getId());
        return sections.stream().filter(p).findFirst().get();
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

    public void addMark(StudentCard studentCard, Subject subject, byte mark) throws EntityNotFoundException, ValidationException{
        Section section =  findSection(subject);
        section.addMark(studentCard, mark);
    }

    private <T> void validateIfExists(List<T> list, Predicate<T> predicate, String objectName, long id) throws EntityNotFoundException{
        if (list.stream().noneMatch(predicate))
            throw new EntityNotFoundException(objectName + " with id " + id + " doesn't exist");
    }

}
