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
    public ResponseEntity<List<Flight>> getFlight(){

        var Flight = flightService.getAllFlight();
        return ResponseEntity.ok(Flight);
    }
    @GetMapping(path="/{id}")
    public ResponseEntity<Flight> search(@PathVariable Long id){
        var flight = flightService.getByFlightId(id);
        return ResponseEntity.ok(flight);
    }
    @PostMapping("/flight")
    public ResponseEntity<Object> createFlight(@RequestBody FlightSaveRequest request) {
        var flightSaveResponse = flightService.save(request);
        var response =  BaseResponse.<FlightSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(flightSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }

  /*  @PostMapping
    public ResponseEntity<FlightSaveResponse> createFlight(@RequestBody FlightSaveRequest request){
        FlightSaveResponse savedFlight = flightService.save(request);
        return ResponseEntity.ok(savedFlight);
    }
*/


}
