package io.upschool.controller;

import io.upschool.dto.*;
import io.upschool.entity.Airport;
import io.upschool.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@RequiredArgsConstructor
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public ResponseEntity<BaseResponse<List<Airport>>> getAirport() {
        var airports = airportService.getAllAirports();
        var response = BaseResponse.<List<Airport>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airports)
                .build();
        return ResponseEntity.ok(response);
    }
    @GetMapping(path="/{id}")
    public ResponseEntity<BaseResponse<Airport>> search(@PathVariable Long id) {
        var airport = airportService.getByAirportId(id);
        var response = BaseResponse.<Airport>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(airport)
                .build();
        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<Object> createAirport(@RequestBody AirportSaveRequest request) {
        var airportSaveResponse = airportService.save(request);
        var response =  BaseResponse.<AirportSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(airportSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }

}
