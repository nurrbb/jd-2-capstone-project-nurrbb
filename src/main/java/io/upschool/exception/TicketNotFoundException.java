package io.upschool.exception;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(Long id) {
        super("Ticket with ID " + id + " not found!");
    }
    
    public TicketNotFoundException(String ticketNumber) {
        super("Ticket with number " + ticketNumber + " not found!");
    }
}

