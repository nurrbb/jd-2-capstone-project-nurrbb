package io.upschool.service;


import io.upschool.entity.Ticket;
import io.upschool.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;
    public List<Ticket> findAllByTicketNumberIs(String ticketNumber){
        return ticketRepository.findAllByTicketNumberIs(ticketNumber);
    }
    public  List<Ticket> getAllTicket(){
        return ticketRepository.findAll();
    }
    public Ticket Save(Ticket  ticket){
        return ticketRepository.save(ticket);
    }

}
