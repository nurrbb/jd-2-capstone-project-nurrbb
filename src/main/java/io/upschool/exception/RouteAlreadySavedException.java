package io.upschool.exception;

public class RouteAlreadySavedException extends RuntimeException {
    public RouteAlreadySavedException() {
        super("Route is already saved.");
    }
}
