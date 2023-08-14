package io.upschool.exception;

public class AirlineNotFoundException extends RuntimeException {
    public AirlineNotFoundException(Long id) {
        super("Airline with ID " + id + " not found!");
    }
}
