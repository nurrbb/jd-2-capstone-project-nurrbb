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
    public ResponseEntity<BaseResponse<List<Ticket>>> getTicket() {
        var tickets = ticketService.getAllTicket();
        var response = BaseResponse.<List<Ticket>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(tickets)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Ticket>> search(@PathVariable Long id) {
        var ticket = ticketService.getByTicketId(id);
        var response = BaseResponse.<Ticket>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(ticket)
                .build();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Object> createTicket(@RequestBody TicketSaveRequest request) {
        var ticketSaveResponse = ticketService.save(request);
        var response =  BaseResponse.<TicketSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(ticketSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable("id") long id) {
        ticketService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
