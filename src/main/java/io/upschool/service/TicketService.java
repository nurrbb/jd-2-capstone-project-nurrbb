package io.upschool.service;

import io.upschool.dto.TicketSaveRequest;
import io.upschool.dto.TicketSaveResponse;
import io.upschool.entity.Flight;
import io.upschool.entity.Ticket;
import io.upschool.exception.InvalidCreditCardNumberException;
import io.upschool.exception.TicketAlreadySavedException;
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

    @Transactional(readOnly = true)
    public Ticket getByTicketId(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + " not found!"));
    }

    public List<Ticket> findTicketBySurname(String surname){
        return ticketRepository.findAllByPassengerSurname(surname);
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
        if (ticketRepository.existsByPassengerNameAndPassengerSurnameIgnoreCase(request.getPassengerName(), request.getPassengerSurname())) {
            throw new TicketAlreadySavedException();
        }
        if (!isValidCreditCardNumber(request.getCreditCardNumber())) {
            throw new InvalidCreditCardNumberException("Invalid credit card number format.Your credit card number must be 16 digits long and consist of only numbers.");
        }

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
                .maskedCreditCardNumber(maskedCreditCardNumber)
                .flight(flight)
                .build();
    }
    private boolean isValidCreditCardNumber(String creditCardNumber) {
        return creditCardNumber.matches("\\d{16}");
    }

    private String maskCreditCardNumber(String creditCardNumber) {
        String cleanedNumber = creditCardNumber.replaceAll("[\\s- ,]", "");

        int visibleDigits = 4;
        int maskedDigits = cleanedNumber.length() - visibleDigits - 2;
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
