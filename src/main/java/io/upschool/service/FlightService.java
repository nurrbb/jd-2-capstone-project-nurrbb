package io.upschool.service;

import io.upschool.dto.FlightProjection;
import io.upschool.dto.FlightSaveRequest;
import io.upschool.dto.FlightSaveResponse;
import io.upschool.entity.Airline;
import io.upschool.entity.Flight;
import io.upschool.entity.Route;
import io.upschool.exception.FlightNotFoundException;
import io.upschool.repository.FlightRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final AirlineService airlineService;
    private final RouteService routeService;

    public List<Flight> findFlightByNumber(String number){
       return flightRepository.findAllByNumberIs(number);
    }

    /**
     * Optimized method using projection for better performance
     */
    @Transactional(readOnly = true)
    public List<FlightProjection> findFlightByNumberOptimized(String number){
       return flightRepository.findFlightsByNumberOptimized(number);
    }

    @Transactional(readOnly = true)
    public Flight getByFlightId(Long id) {

        return flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException(id));
    }



@Transactional
    public FlightSaveResponse save(FlightSaveRequest request) {

        Airline airline = airlineService.getAirlineById(request.getAirlineId());
        Route route = routeService.getRouteById(request.getRouteId());

        // Calculate duration in minutes
        int durationMinutes = (int) java.time.Duration.between(request.getDepartureTime(), request.getArrivalTime()).toMinutes();

        Flight flight = Flight.builder()
                .airline(airline)
                .route(route)
                .number(request.getNumber())
                .totalSeats(request.getTotalSeats())
                .availableSeats(request.getTotalSeats()) // Initially all seats available
                .departureTime(request.getDepartureTime())
                .arrivalTime(request.getArrivalTime())
                .durationMinutes(durationMinutes)
                .basePrice(request.getBasePrice())
                .build();
        flightRepository.save(flight);
       return FlightSaveResponse.builder()
                .flightID(flight.getFlightID())
                .number(flight.getNumber())
                .airline(flight.getAirline())
                .route(flight.getRoute())
                .totalSeats(flight.getTotalSeats())
                .availableSeats(flight.getAvailableSeats())
                .departureTime(flight.getDepartureTime())
                .arrivalTime(flight.getArrivalTime())
                .durationMinutes(flight.getDurationMinutes())
                .basePrice(flight.getBasePrice())
                .build();
    }
    public Flight Save(Flight  flight){
        return flightRepository.save(flight);
    }
    
    /**
     * Get all flights without pagination (legacy method)
     */
    public List<Flight> getAllFlight(){
        return  flightRepository.findAll();
    }

    /**
     * Paginated search for flights with optimized projection
     * Supports filtering by flight number, airline name, origin/destination airport names
     */
    @Transactional(readOnly = true)
    public Page<FlightProjection> searchFlights(String number, String airlineName, 
                                                 String originAirportName, String destinationAirportName,
                                                 Pageable pageable) {
        return flightRepository.searchFlightsOptimized(
                number, airlineName, originAirportName, destinationAirportName, pageable);
    }

    /**
     * Get all flights with pagination and optimized projection
     */
    @Transactional(readOnly = true)
    public Page<FlightProjection> getAllFlights(Pageable pageable) {
        return flightRepository.findAllFlightsOptimized(pageable);
    }


}
