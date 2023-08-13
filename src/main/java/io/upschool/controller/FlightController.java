package io.upschool.controller;

import io.upschool.entity.Flight;
import io.upschool.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flight")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<List<Flight>> getFlight(){

        var Flight = flightService.getAllFlight();
        return ResponseEntity.ok(Flight);
    }

    @PostMapping
    public ResponseEntity<Flight> createFlight(@RequestBody Flight flight){
        Flight savedFlight = flightService.Save(flight);
        return ResponseEntity.ok(savedFlight);
    }

}
