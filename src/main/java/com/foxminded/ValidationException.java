package com.foxminded;

public class ValidationException extends Exception {

    public ValidationException(){
        System.out.println("ValidationException");
    }

    public ValidationException(String message) {
        System.out.println("ValidationException: " + message);
    }

}
