package io.upschool.exception;

public class InsufficientSeatsException extends RuntimeException {
    public InsufficientSeatsException(String message) {
        super(message);
    }
    
    public InsufficientSeatsException() {
        super("Insufficient seats available for this flight.");
    }
}

