package io.upschool.exception;

public class AirlineAlreadySavedException extends RuntimeException{
    public AirlineAlreadySavedException() {
        super("Airline is already saved.");
    }
}
