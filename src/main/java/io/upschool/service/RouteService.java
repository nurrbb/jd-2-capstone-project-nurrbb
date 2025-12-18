package io.upschool.service;

import io.upschool.dto.RouteProjection;
import io.upschool.dto.RouteSaveRequest;
import io.upschool.dto.RouteSaveResponse;
import io.upschool.entity.Airport;
import io.upschool.entity.Route;
import io.upschool.exception.InvalidRouteException;
import io.upschool.exception.RouteAlreadySavedException;
import io.upschool.exception.RouteNotFoundException;
import io.upschool.repository.RouteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                .orElseThrow(() -> new RouteNotFoundException(id));
    }
    @Transactional
    public RouteSaveResponse save(RouteSaveRequest request) {
        Airport originAirport = airportService.getByAirportId(request.getOriginAirportId());
        Airport destinationAirport = airportService.getByAirportId(request.getDestinationAirportId());

        if (originAirport.getId() == destinationAirport.getId()) {
            throw new InvalidRouteException("Origin and destination airports cannot be the same.");
        }

        if (routeRepository.existsByOriginAirportAndDestinationAirport(originAirport, destinationAirport)) {
            throw new RouteAlreadySavedException();
        }

        Route route = Route.builder()
                .originAirport(originAirport)
                .destinationAirport(destinationAirport)
                .averageDurationMinutes(request.getAverageDurationMinutes())
                .build();

        routeRepository.save(route);
        return RouteSaveResponse.builder()
                .routeID(route.getRouteID())
                .originAirport(route.getOriginAirport())
                .destinationAirport(route.getDestinationAirport())
                .averageDurationMinutes(route.getAverageDurationMinutes())
                .build();
    }

    /**
     * Search routes by airports without pagination (legacy method)
     */
    public List<Route> searchRoutesByAirports(Long originAirportId, Long destinationAirportId) {
        return routeRepository.findByOriginAirportIdAndDestinationAirportId(originAirportId, destinationAirportId);
    }

    /**
     * Optimized method using projection for better performance
     */
    @Transactional(readOnly = true)
    public List<RouteProjection> searchRoutesByAirportsOptimized(Long originAirportId, Long destinationAirportId) {
        return routeRepository.findRoutesByAirportsOptimized(originAirportId, destinationAirportId);
    }

    /**
     * Get all routes without pagination (legacy method)
     */
    public List<Route> getAllRoute(){
        return  routeRepository.findAll();
    }

    /**
     * Paginated search for routes with optimized projection
     * Supports filtering by origin/destination airport IDs and names
     */
    @Transactional(readOnly = true)
    public Page<RouteProjection> searchRoutes(Long originAirportId, Long destinationAirportId,
                                               String originAirportName, String destinationAirportName,
                                               Pageable pageable) {
        return routeRepository.searchRoutesOptimized(
                originAirportId, destinationAirportId, originAirportName, destinationAirportName, pageable);
    }

    /**
     * Get all routes with pagination and optimized projection
     */
    @Transactional(readOnly = true)
    public Page<RouteProjection> getAllRoutes(Pageable pageable) {
        return routeRepository.findAllRoutesOptimized(pageable);
    }


}
