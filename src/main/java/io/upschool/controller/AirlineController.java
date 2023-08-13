package io.upschool.controller;

import io.upschool.entity.Airline;
import io.upschool.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airline")
@RequiredArgsConstructor

public class AirlineController {
    private final AirlineService airlineService;

    @GetMapping
    public ResponseEntity<List<Airline>> getAirlines(){

        var airline = airlineService.getAllAirline();
        return ResponseEntity.ok(airline);
    }

    @PostMapping
    public ResponseEntity<Airline> createAirline(@RequestBody Airline airline){
        Airline savedAirline = airlineService.save(airline);
        return ResponseEntity.ok(savedAirline);
    }

}
