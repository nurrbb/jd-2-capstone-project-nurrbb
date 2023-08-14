package io.upschool.controller;

import io.upschool.dto.AirlineSaveRequest;
import io.upschool.dto.AirlineSaveResponse;
import io.upschool.dto.BaseResponse;
import io.upschool.entity.Airline;
import io.upschool.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/airline")
    public ResponseEntity<Object> createAirline(@RequestBody AirlineSaveRequest request) {
        var airlineSaveResponse = airlineService.save(request);
        var response =  BaseResponse.<AirlineSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(airlineSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping(path="/{id}")
    public ResponseEntity<Airline> search(@PathVariable Long id){
        var airline = airlineService.getByAirlineId(id);
        return ResponseEntity.ok(airline);
    }

}
