package io.upschool.repository;

import io.upschool.entity.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RouteRepository extends JpaRepository<Route,Long> {
    List<Route> findByOriginAirport_NameAndDestinationAirport_Name(String originAirportName, String destinationAirportName);



}
