package io.upschool.service;

import io.upschool.entity.Flight;
import io.upschool.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;

    public List<Flight> findFlightByNumber(String number){
       return flightRepository.findAllByNumberIs(number);
    }
    public Flight Save(Flight  flight){
        return flightRepository.save(flight);
    }
    public List<Flight> getAllFlight(){
        return  flightRepository.findAll();
    }


}
