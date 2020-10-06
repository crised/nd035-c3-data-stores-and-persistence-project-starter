package com.udacity.jdnd.course3.critter.service;

public class AppException extends RuntimeException {

    public AppException() {
    }

    public AppException(String message) {
        super(message);
    }
}
