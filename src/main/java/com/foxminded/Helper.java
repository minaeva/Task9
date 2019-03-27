package com.foxminded;

import java.util.List;
import java.util.function.Predicate;

public class Helper {

    private static long id = 1;

    public static long generateNewId(){
        return id++;
    }

     public static <T> void validateNameIsUnique(List<T> list,
                                                 Predicate<T> predicate,
                                                 String objectName,
                                                 String name) throws IllegalArgumentException{
        if (list.stream().anyMatch(predicate)){
            throw new IllegalArgumentException(objectName + " with name " + name + " already exists");
        }
    }

    public static <T> T findObjectIfExists(List<T> list, Predicate<T> predicate, String objectName, long id) throws IllegalArgumentException{
        return list
                .stream()
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(objectName + " with id " + id + " doesn't exist"));
    }

    public static <T> T findObjectByNameIfExists(List<T> list, Predicate<T> predicate, String object, String objectName) throws IllegalArgumentException{
        return list
                .stream()
                .filter(predicate)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(object + " with name " + objectName + " doesn't exist"));
    }
}

