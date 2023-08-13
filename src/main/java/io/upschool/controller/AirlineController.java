package io.upschool.controller;

import io.upschool.dto.AirlineSaveRequest;
import io.upschool.dto.AirlineSaveResponse;
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
    public ResponseEntity<AirlineSaveResponse> createAirline(@RequestBody AirlineSaveRequest airlineSaveRequest){
        var response = airlineService.save(airlineSaveRequest);
        return ResponseEntity.ok(response);
    }

}
