package com.foxminded;

import lombok.Data;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Data
public class Group {

    private long id;
    private String name;
    private long facultyId;
    private List<StudentCard> students = new ArrayList<>();

    public Group(){}

    public Group(String name){
        this.name = name;
    }

    public StudentCard takeStudent(StudentCard studentCard){
        students.add(studentCard);
        studentCard.setGroupId(this.id);
        return studentCard;
    }

    public StudentCard findStudent(long studentId) throws EntityNotFoundException{
        Predicate<StudentCard> p = s -> s.getId() == studentId;
        if (students.stream().noneMatch(p)) throw new EntityNotFoundException("Student with id " + studentId + " doesn't exist");
        return students.stream().filter(p).collect(Collectors.toList()).get(0);
    }

    public void dismissStudent(long studentId) throws EntityNotFoundException{
        StudentCard student = findStudent(studentId);
        student.dismiss();
        students.remove(student);
    }

    public List<StudentCard> findStudents(){
        return students;
    }

    public void dismantle(){
        for (StudentCard student: students) {
            student.setGroupId(0);
        }
    }

    @Override
    public boolean equals(Object groupToCheck){
        if (groupToCheck == this) return true;
        if (!(groupToCheck instanceof Group)) return false;
        Group group = (Group) groupToCheck;
        return group.getName().equals(name) && group.getId() == id;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + (int)id;
        result = 31 * result + (int)facultyId;
        return result;
    }
}
