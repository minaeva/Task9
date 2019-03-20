package com.foxminded;

public class IdGenerator {

private static long id = 1;

public static long newId(){
    return id++;}

}
