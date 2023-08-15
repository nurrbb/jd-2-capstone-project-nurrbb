package io.upschool.service;

import io.upschool.dto.AirlineSaveRequest;
import io.upschool.dto.AirlineSaveResponse;
import io.upschool.dto.Airlinedto;
import io.upschool.entity.Airline;
import io.upschool.exception.AirlineAlreadySavedException;
import io.upschool.exception.AirlineNotFoundException;
import io.upschool.repository.AirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AirlineService {
    private final AirlineRepository airlineRepository;

    @Transactional(readOnly = true)
    public Airlinedto getByAirlineId(Long id) {

        Airline airline =  airlineRepository.findById(id)
                .orElseThrow(() -> new AirlineNotFoundException(id));
        return Airlinedto.builder()
                .id(airline.getAirlineID())
                .name(airline.getName())
                .build();

    }

    @Transactional(readOnly = true)
    public Airline getAirlineById(Long id) {

        return   airlineRepository.findById(id)
                .orElseThrow(() -> new AirlineNotFoundException(id));

    }

    @Transactional
    public AirlineSaveResponse save(AirlineSaveRequest airlineSaveRequest){
        String lowercaseName = airlineSaveRequest.getName().toLowerCase();
        if (airlineRepository.existsByName(airlineSaveRequest.getName())) {
            throw new AirlineAlreadySavedException();
        }
        var newAirline = Airline.builder()
                .name(airlineSaveRequest.getName())
                .build();
        Airline airline = airlineRepository.save(newAirline);
        return AirlineSaveResponse.builder()
                .id(airline.getAirlineID())
                .name(airline.getName())
                .build();
    }
    public Airline save(Airline airline) {
        return airlineRepository.save(airline);
    }

    public  List<Airline> getAllAirline(){
        return airlineRepository.findAll();
    }



}
