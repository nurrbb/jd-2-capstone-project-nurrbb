package io.upschool.exception;

public class RouteNotFoundException extends RuntimeException {
    public RouteNotFoundException(Long id) {
        super("Route with ID " + id + " not found!");
    }
}

