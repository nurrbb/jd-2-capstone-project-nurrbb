package io.upschool.service;

import io.upschool.dto.TicketSaveRequest;
import io.upschool.dto.TicketSaveResponse;
import io.upschool.entity.Flight;
import io.upschool.entity.Ticket;
import io.upschool.repository.TicketRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    private final FlightService flightService ;


    public List<Ticket> findTicketBySurname(String surname){
        return ticketRepository.findAllByPassengerSurname(surname);
    }
    @Transactional(readOnly = true)
    public Ticket getByTicketId(Long id) {

        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + " not found!"));
    }
    public List<Ticket> findAllByTicketNumberIs(String ticketNumber){
        return ticketRepository.findAllByTicketNumberIs(ticketNumber);
    }
    public  List<Ticket> getAllTicket(){
        return ticketRepository.findAll();
    }

    @Transactional
    public TicketSaveResponse save(TicketSaveRequest request) {
        Flight flight = flightService.getByFlightId(request.getFlightID());

        String maskedCreditCardNumber = maskCreditCardNumber(request.getCreditCardNumber());

        Ticket ticket = Ticket.builder()
                .ticketNumber(RandomStringUtils.randomAlphanumeric(4))
                .passengerName(request.getPassengerName())
                .passengerSurname(request.getPassengerSurname())
                .flight(flight)
                .maskedCreditCardNumber(maskedCreditCardNumber)
                .build();

        ticketRepository.save(ticket);

        return TicketSaveResponse.builder()
                .ticketId(ticket.getTicketID())
                .ticketNumber(ticket.getTicketNumber())
                .passengerName(ticket.getPassengerName())
                .passengerSurname(ticket.getPassengerSurname())
                .flight(flight)
                .build();
    }

    private String maskCreditCardNumber(String creditCardNumber) {
        String cleanedNumber = creditCardNumber.replaceAll("[\\s- ,]", "");

        int visibleDigits = 3;
        int maskedDigits = cleanedNumber.length() - visibleDigits - 4;
        String maskedPart = "*".repeat(maskedDigits);
        int lastDigits = 2;
        return cleanedNumber.substring(0, visibleDigits) + maskedPart + cleanedNumber.substring(cleanedNumber.length() - lastDigits);

    }

    public void delete(Long id) {
        var Ticket = ticketRepository.findById(id).get();
        Ticket.setActive(false);
        ticketRepository.save(Ticket);
    }

}
