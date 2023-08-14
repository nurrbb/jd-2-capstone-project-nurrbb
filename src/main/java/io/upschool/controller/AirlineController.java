package io.upschool.controller;

import io.upschool.dto.AirlineSaveRequest;
import io.upschool.dto.AirlineSaveResponse;
import io.upschool.entity.Airline;
import io.upschool.entity.Airport;
import io.upschool.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airlines")
@RequiredArgsConstructor

public class AirlineController {
    private final AirlineService airlineService;

    @GetMapping
    public ResponseEntity<List<Airline>> getAirlines(){
        var airline = airlineService.getAllAirline();
        return ResponseEntity.ok(airline);
    }

    @PostMapping
    public ResponseEntity<Airline> createAirline(@RequestBody Airline airline) {
        return ResponseEntity.ok(airlineService.save(airline));
    }
    @GetMapping(path="/{id}")
    public ResponseEntity<Airline> search(@PathVariable Long id){
        var airline = airlineService.getByAirlineId(id);
        return ResponseEntity.ok(airline);
    }

}
