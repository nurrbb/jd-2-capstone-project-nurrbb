package io.upschool.exception;

public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(Long id) {
        super("Flight with ID " + id + " not found!");
    }
    
    public FlightNotFoundException(String number) {
        super("Flight with number " + number + " not found!");
    }
}

