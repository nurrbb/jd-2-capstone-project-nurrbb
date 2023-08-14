package io.upschool.service;

import io.upschool.dto.TicketSaveRequest;
import io.upschool.dto.TicketSaveResponse;
import io.upschool.entity.Flight;
import io.upschool.entity.Ticket;
import io.upschool.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final FlightService flightService ;

    public List<Ticket> findAllByTicketNumberIs(String ticketNumber){
        return ticketRepository.findAllByTicketNumberIs(ticketNumber);
    }
    public  List<Ticket> getAllTicket(){
        return ticketRepository.findAll();
    }

    public TicketSaveResponse save(TicketSaveRequest request, String creditCardNumber) {
        Flight flight = flightService.getByFlightId(request.getFlightID());

        String maskedCreditCardNumber = maskCreditCardNumber(creditCardNumber);

        Ticket ticket = Ticket.builder()
                .ticketNumber(RandomStringUtils.randomNumeric(4))
                .passengerName(request.getPassengerName())
                .passengerSurname(request.getPassengerSurname())
                .flight(flight)
                .active(true)
                .build();

        ticketRepository.save(ticket);

        return TicketSaveResponse.builder()
                .ticketId(ticket.getTicketID())
                .ticketNumber(ticket.getTicketNumber())
                .passengerName(ticket.getPassengerName())
                .passengerSurname(ticket.getPassengerSurname())
                .flight(ticket.getFlight())
                .build();
    }

    private String maskCreditCardNumber(String creditCardNumber) {
        String cleanedNumber = creditCardNumber.replaceAll("[\\s- ,]", "");

        int visibleDigits = 6;
        int maskedDigits = cleanedNumber.length() - visibleDigits - 4;
        String maskedPart = "*".repeat(maskedDigits);
        int lastDigits = 4;

        return cleanedNumber.substring(0, visibleDigits) +
                maskedPart +
                cleanedNumber.substring(cleanedNumber.length() - lastDigits);
    }

    public void delete(Long id) {
        var Ticket = ticketRepository.findById(id).get();
        Ticket.setActive(false);
        ticketRepository.save(Ticket);
    }

}
