package io.upschool.controller;


import io.upschool.entity.Ticket;
import io.upschool.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ticket")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping
    public ResponseEntity<List<Ticket>> getTicket(){

        var ticket = ticketService.getAllTicket();
        return ResponseEntity.ok(ticket);
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket){
        Ticket savedTicket = ticketService.Save(ticket);
        return ResponseEntity.ok(savedTicket);
    }
}
