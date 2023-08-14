package io.upschool.exception;

public class TicketAlreadySavedException extends RuntimeException{
    public TicketAlreadySavedException() {
        super("A ticket has already been purchased by this passenger.");
    }
}
