package io.upschool.controller;

import io.upschool.dto.*;
import io.upschool.entity.Flight;
import io.upschool.service.FlightService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
@RequiredArgsConstructor
public class FlightController {
    private final FlightService flightService;

    /**
     * Get all flights without pagination (legacy endpoint for backward compatibility)
     */
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

    /**
     * Search for existing flights with pagination and sorting support
     * Query parameters:
     *   - page: page number (default: 0)
     *   - size: page size (default: 10)
     *   - sort: sort field and direction (e.g., "flightID,asc" or "number,desc")
     *   - number: filter by flight number (optional)
     *   - airlineName: filter by airline name (optional)
     *   - originAirportName: filter by origin airport name (optional)
     *   - destinationAirportName: filter by destination airport name (optional)
     * 
     * Example: /api/flights/search?page=0&size=20&sort=number,asc&number=12345
     */
    @GetMapping("/search")
    public ResponseEntity<BaseResponse<Page<FlightProjection>>> searchFlights(
            @RequestParam(required = false) String number,
            @RequestParam(required = false) String airlineName,
            @RequestParam(required = false) String originAirportName,
            @RequestParam(required = false) String destinationAirportName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "flightID,asc") String sort) {
        
        String[] sortParams = sort.split(",");
        Sort.Direction direction = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc") 
                ? Sort.Direction.DESC 
                : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortParams[0]));
        
        var flights = flightService.searchFlights(
                number, airlineName, originAirportName, destinationAirportName, pageable);
        var response = BaseResponse.<Page<FlightProjection>>builder()
                .status(HttpStatus.OK.value())
                .isSuccess(true)
                .data(flights)
                .build();
        return ResponseEntity.ok(response);
    }

    /**
     * Get all flights with pagination and sorting support
     * Query parameters:
     *   - page: page number (default: 0)
     *   - size: page size (default: 10)
     *   - sort: sort field and direction (e.g., "flightID,asc" or "number,desc")
     */
    @GetMapping("/paginated")
    public ResponseEntity<BaseResponse<Page<FlightProjection>>> getAllFlightsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "flightID,asc") String sort) {
        
        String[] sortParams = sort.split(",");
        Sort.Direction direction = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc") 
                ? Sort.Direction.DESC 
                : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortParams[0]));
        
        var flights = flightService.getAllFlights(pageable);
        var response = BaseResponse.<Page<FlightProjection>>builder()
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
    public ResponseEntity<Object> createFlight(@Valid @RequestBody FlightSaveRequest request) {
        var flightSaveResponse = flightService.save(request);
        var response =  BaseResponse.<FlightSaveResponse>builder()
                .status(HttpStatus.CREATED.value())
                .isSuccess(true)
                .data(flightSaveResponse)
                .build();
        return ResponseEntity.ok(response);
    }

}
