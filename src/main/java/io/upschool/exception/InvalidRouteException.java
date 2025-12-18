package io.upschool.exception;

public class InvalidRouteException extends RuntimeException {
    public InvalidRouteException(String message) {
        super(message);
    }
    
    public InvalidRouteException() {
        super("Invalid route configuration.");
    }
}
