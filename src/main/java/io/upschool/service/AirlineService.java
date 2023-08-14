package io.upschool.service;

import io.upschool.dto.AirlineSaveRequest;
import io.upschool.dto.AirlineSaveResponse;
import io.upschool.entity.Airline;
import io.upschool.repository.AirlineRepository;
import jakarta.transaction.Transactional;
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


    public Airline getByAirlineId(Long id) {

        return airlineRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airline with ID " + id + " not found!"));
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
    public Airline save(Airline airline) {
        return airlineRepository.save(airline);
    }

    public  List<Airline> getAllAirline(){
        return airlineRepository.findAll();
    }

}
