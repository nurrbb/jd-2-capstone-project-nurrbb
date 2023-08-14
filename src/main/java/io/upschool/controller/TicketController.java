package io.upschool.controller;

import io.upschool.dto.*;
import io.upschool.entity.Ticket;
import io.upschool.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
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
    @PostMapping("/ticket")
    public ResponseEntity<Object> createTicket(@RequestBody TicketSaveRequest request) {
        var ticketSaveResponse = ticketService.save(request);
        var response =  BaseResponse.<TicketSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(ticketSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public  void deleteTicket(@PathVariable("id") long id){
        ticketService.delete(id);
    }

}
