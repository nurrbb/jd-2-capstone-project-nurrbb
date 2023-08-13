package io.upschool.service;

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

    public Airline save(Airline airline){
      return airlineRepository.save(airline);
    }

    public  List<Airline> getAllAirline(){
        return airlineRepository.findAll();
    }

}
