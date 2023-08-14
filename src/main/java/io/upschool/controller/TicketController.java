package io.upschool.controller;

import io.upschool.dto.TicketSaveRequest;
import io.upschool.dto.TicketSaveResponse;
import io.upschool.entity.Flight;
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
    @GetMapping(path="/{id}")
    public ResponseEntity<Ticket> search(@PathVariable Long id){
        var ticket = ticketService.getByTicketId(id);
        return ResponseEntity.ok(ticket);
    }
    @PostMapping
    public ResponseEntity<TicketSaveResponse> createTicket(@RequestBody TicketSaveRequest request){
        TicketSaveResponse savedTicket = ticketService.save(request);
        return ResponseEntity.ok(savedTicket);
    }

    @DeleteMapping("{id}")
    public  void deleteTicket(@PathVariable("id") long id){
        ticketService.delete(id);
    }

}
