package io.upschool.service;

import io.upschool.dto.RouteSaveRequest;
import io.upschool.dto.RouteSaveResponse;
import io.upschool.entity.Airport;
import io.upschool.entity.Route;
import io.upschool.repository.RouteRepository;
import jakarta.transaction.Transactional;
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

    public Route getRouteById(Long id){

        return routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route with ID " + id + " not found!"));
    }
    @Transactional
    public RouteSaveResponse save(RouteSaveRequest request) {
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
