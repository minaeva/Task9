package com.foxminded;

import lombok.Getter;
import lombok.Setter;
import java.util.*;

public class Group {

    @Getter @Setter private long id;
    @Getter @Setter private String name;
    @Getter @Setter private long facultyId;
    @Getter @Setter private Map<Long, StudentCard> students;

    public Group(){}

    public Group(String name){
        this.name = name;
    }

    public StudentCard takeStudent(StudentCard studentCard){
        students.put(studentCard.getId(), studentCard);
        studentCard.setGroupId(this.id);
        return studentCard;
    }

    public void dissmissStudent(long studentId){
        //todo
        students.remove(studentId);
    }

    public ArrayList<StudentCard> findStudents(){
        //todo
        return new ArrayList<>(students.values());
    }

    public void dismantle(){
        //todo
    }

    @Override
    public boolean equals(Object groupToCheck){
        if (groupToCheck == this) return true;
        if (!(groupToCheck instanceof Group)) return false;
        Group group = (Group) groupToCheck;
        return group.getName().equals(name) && group.getId() == id;
    }
}
