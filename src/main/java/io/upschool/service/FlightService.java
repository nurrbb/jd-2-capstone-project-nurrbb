package io.upschool.service;

import io.upschool.dto.FlightSaveRequest;
import io.upschool.dto.FlightSaveResponse;
import io.upschool.entity.Airline;
import io.upschool.entity.Flight;
import io.upschool.entity.Route;
import io.upschool.repository.FlightRepository;
import jakarta.transaction.Transactional;
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

    public Flight getByFlightId(Long id) {

        return flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight with ID " + id + " not found!"));
    }

@Transactional
    public FlightSaveResponse save(FlightSaveRequest request) {

        Airline airline = airlineService.getByAirlineId(request.getAirlineId());
        Route route = routeService.getRouteById(request.getRouteId());
        Flight flight = Flight.builder()
                .airline(airline)
                .route(route)
                .number(RandomStringUtils.randomNumeric(5))
                .build();
        flightRepository.save(flight);
       return FlightSaveResponse.builder()
                .flightID(flight.getFlightID())
                .number(flight.getNumber())
                .airline(flight.getAirline())
                .route(flight.getRoute())
                .build();
    }
    public Flight Save(Flight  flight){
        return flightRepository.save(flight);
    }
    public List<Flight> getAllFlight(){
        return  flightRepository.findAll();
    }


}
