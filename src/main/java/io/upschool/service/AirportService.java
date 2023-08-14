package io.upschool.service;

import io.upschool.entity.Airport;
import io.upschool.repository.AirportRepository;
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
    public Airport getByAirportId(Long id) {

        return airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport with ID " + id + " not found!"));
    }

    public Airport save(Airport airport){
        return  airportRepository.save(airport);
    }

    public List<Airport> getAllAirports(){
     return  airportRepository.findAll();
    }



}
