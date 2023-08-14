package io.upschool.controller;

import io.upschool.dto.*;
import io.upschool.entity.Flight;
import io.upschool.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<Flight>>> getFlight() {
        var flights = flightService.getAllFlight();
        var response = BaseResponse.<List<Flight>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(flights)
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<Flight>> search(@PathVariable Long id) {
        var flight = flightService.getByFlightId(id);
        var response = BaseResponse.<Flight>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(flight)
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<Object> createFlight(@RequestBody FlightSaveRequest request) {
        var flightSaveResponse = flightService.save(request);
        var response =  BaseResponse.<FlightSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(flightSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }

}
