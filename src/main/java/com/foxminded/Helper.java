package com.foxminded;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class Helper {

    private static long id = 1;

    public static long generateNewId(){
        return id++;
    }

     public static <T> void validateNameIsUnique(List<T> list, Predicate<T> predicate, String objectName, String name) throws IllegalArgumentException{
        if (list.stream().anyMatch(predicate)){
            throw new IllegalArgumentException(objectName + " with name " + name + " already exists");
        }
    }

    public static <T> T validateObjectExists(List<T> list, Predicate<T> predicate, String objectName, long id) throws IllegalArgumentException{
        Optional<T> found = list.stream().filter(predicate).findFirst();
        if (!found.isPresent()) {
            throw new IllegalArgumentException(objectName + " with id " + id + " doesn't exist");
        }
        else{
            return found.get();
        }
    }

}
