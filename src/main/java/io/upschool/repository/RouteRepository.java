package io.upschool.repository;

import io.upschool.entity.Airport;
import io.upschool.entity.Route;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RouteRepository extends JpaRepository<Route,Long> {
    List<Route> findByOriginAirportIdAndDestinationAirportId(Long originAirportId, Long destinationAirportId);
    boolean existsByOriginAirportAndDestinationAirport(Airport originAirport, Airport destinationAirport);




}
