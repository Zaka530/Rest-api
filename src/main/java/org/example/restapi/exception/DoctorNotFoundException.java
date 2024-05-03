package org.example.restapi.exception;

public class DoctorNotFoundException extends Exception {
    public DoctorNotFoundException(String message) {
        super(message);
    }
}
