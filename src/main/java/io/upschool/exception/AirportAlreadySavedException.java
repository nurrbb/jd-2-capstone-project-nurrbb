package io.upschool.exception;

public class AirportAlreadySavedException extends RuntimeException {
    public AirportAlreadySavedException() {
        super("Airport is already saved.");
    }
}
