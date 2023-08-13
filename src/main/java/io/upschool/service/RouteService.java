package io.upschool.service;


import io.upschool.entity.Route;
import io.upschool.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {
    private final RouteRepository routeRepository;

    public Route Save(Route  route){
        return routeRepository.save(route);
    }
    public List<Route> searchRoutesByAirports(String originAirportName, String destinationAirportName) {
        return routeRepository.findByOriginAirport_NameAndDestinationAirport_Name(originAirportName, destinationAirportName);
    }
    public List<Route> getAllRoute(){
        return  routeRepository.findAll();
    }
}
