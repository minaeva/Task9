package com.foxminded;

public class EntityNotFoundException extends Exception {

    public EntityNotFoundException() {
        System.out.println("EntityNotFoundException");

    }

    public EntityNotFoundException(String message) {
        System.out.println("EntityNotFoundException: " + message);
    }
}