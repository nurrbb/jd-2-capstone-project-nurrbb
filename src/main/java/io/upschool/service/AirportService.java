package io.upschool.service;

import io.upschool.dto.AirportSaveRequest;
import io.upschool.dto.AirportSaveResponse;
import io.upschool.entity.Airport;
import io.upschool.repository.AirportRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;

    public List<Airport> findAirportByName(String name){
        return airportRepository.findAllByNameIs(name);
    }

    @Transactional(readOnly = true)
    public Airport getByAirportId(Long id) {

        return airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException( id + " not found!"));
    }

    @Transactional //save delete
    public AirportSaveResponse save(AirportSaveRequest request){
        var newAirport = Airport
                .builder()
                .name(request.getName())
                .location(request.getLocation())
                .build();
        Airport savedAirport = airportRepository.save(newAirport);
        return AirportSaveResponse
                .builder()
                .location(savedAirport.getLocation())
                .name(savedAirport.getName())
                .id(savedAirport.getId()).build();

    }

    public Airport save(Airport airport){
        return  airportRepository.save(airport);
    }

    public List<Airport> getAllAirports(){
     return  airportRepository.findAll();
    }



}
