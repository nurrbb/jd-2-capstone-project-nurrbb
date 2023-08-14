package io.upschool.service;

import io.upschool.dto.AirportSaveRequest;
import io.upschool.dto.AirportSaveResponse;
import io.upschool.entity.Airport;
import io.upschool.exception.AirportAlreadySavedException;
import io.upschool.repository.AirportRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;


    @Transactional(readOnly = true)
    public Airport getByAirportId(Long id) {

        return airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException( id + " not found!"));
    }

    @Transactional //save delete
    public AirportSaveResponse save(AirportSaveRequest request){
        String lowercaseName = request.getName().toLowerCase();
        if (airportRepository.existsByName(request.getName())) {
            throw new AirportAlreadySavedException();
        }
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
