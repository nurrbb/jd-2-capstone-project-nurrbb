package io.upschool.service;

import io.upschool.dto.AirlineSaveRequest;
import io.upschool.dto.AirlineSaveResponse;
import io.upschool.entity.Airline;
import io.upschool.repository.AirlineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AirlineService {
    private final AirlineRepository airlineRepository;

    public List<Airline> findAirlineByName(String name){
       return airlineRepository.findAllByNameIs(name);
    }


    public AirlineSaveResponse save(AirlineSaveRequest airlineSaveRequest){
        var newAirline = Airline
                .builder()
                .name(airlineSaveRequest.getName())
                .build();
        Airline savedAirline = airlineRepository.save(newAirline);
        return AirlineSaveResponse
                .builder().name(savedAirline.getName())
                .id(savedAirline.getAirlineID()).build();
    }

    public  List<Airline> getAllAirline(){
        return airlineRepository.findAll();
    }

}
