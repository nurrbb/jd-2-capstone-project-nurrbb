package io.upschool.controller;

import io.upschool.entity.Airport;
import io.upschool.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airport")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public ResponseEntity<List<Airport>> getAirport(){
        var airport = airportService.getAllAirports();
        return ResponseEntity.ok(airport);
    }

    // airline için
    @GetMapping(path="/{id}")
    public ResponseEntity<Airport> search(@PathVariable Long id){
        var airport = airportService.getByAirportId(id);
        return ResponseEntity.ok(airport);
    }
    @PostMapping
    public ResponseEntity<Airport> createAirport(@RequestBody Airport airport){
        Airport savedAirport = airportService.save(airport);
        return ResponseEntity.ok(savedAirport);
    }



}
