package io.upschool.service;

import io.upschool.dto.RouteSaveRequest;
import io.upschool.dto.RouteSaveResponse;
import io.upschool.entity.Airport;
import io.upschool.entity.Route;
import io.upschool.exception.RouteAlreadySavedException;
import io.upschool.repository.RouteRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;
    private final AirportService airportService;

    public Route Save(Route  route){
        return routeRepository.save(route);
    }

    @Transactional(readOnly = true)
    public Route getRouteById(Long id){
        return routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(id + " not found!"));
    }
    @Transactional
    public RouteSaveResponse save(RouteSaveRequest request) {
        Airport originAirport = airportService.getByAirportId(request.getOriginAirportId());
        Airport destinationAirport = airportService.getByAirportId(request.getDestinationAirportId());

        if (originAirport.getId() == destinationAirport.getId()) {
            throw new RuntimeException("Origin and destination airports cannot be the same.");
        }

        if (routeRepository.existsByOriginAirportAndDestinationAirport(originAirport, destinationAirport)) {
            throw new RouteAlreadySavedException();
        }

        Airport origin = airportService.getByAirportId(request.getOriginAirportId());
        Airport destination = airportService.getByAirportId(request.getDestinationAirportId());

        Route route = Route.builder()
                .originAirport(origin)
                .destinationAirport(destination)
                .build();

        routeRepository.save(route);
        return RouteSaveResponse.builder()
                .routeID(route.getRouteID())
                .originAirport(route.getOriginAirport())
                .destinationAirport(route.getDestinationAirport())
                .build();
    }

    public List<Route> searchRoutesByAirports(Long originAirportId, Long destinationAirportId) {
        return routeRepository.findByOriginAirportIdAndDestinationAirportId(originAirportId, destinationAirportId);
    }

    public List<Route> getAllRoute(){
        return  routeRepository.findAll();
    }


}
