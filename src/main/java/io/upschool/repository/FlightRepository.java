package io.upschool.repository;

import io.upschool.dto.FlightProjection;
import io.upschool.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {

    List<Flight>findAllByNumberIs(String number);

    /**
     * Optimized query with projection - selects only necessary columns
     * Improves performance by avoiding full entity loading
     */
    @Query("SELECT f.flightID as flightID, f.number as number, " +
           "a.airlineID as airlineId, a.name as airlineName, " +
           "r.routeID as routeId, " +
           "oa.Id as originAirportId, oa.name as originAirportName, oa.location as originAirportLocation, " +
           "da.Id as destinationAirportId, da.name as destinationAirportName, da.location as destinationAirportLocation " +
           "FROM Flight f " +
           "JOIN f.airline a " +
           "JOIN f.route r " +
           "JOIN r.originAirport oa " +
           "JOIN r.destinationAirport da " +
           "WHERE f.number = :number")
    List<FlightProjection> findFlightsByNumberOptimized(@Param("number") String number);

    /**
     * Paginated search for flights with optimized projection
     */
    @Query("SELECT f.flightID as flightID, f.number as number, " +
           "a.airlineID as airlineId, a.name as airlineName, " +
           "r.routeID as routeId, " +
           "oa.Id as originAirportId, oa.name as originAirportName, oa.location as originAirportLocation, " +
           "da.Id as destinationAirportId, da.name as destinationAirportName, da.location as destinationAirportLocation " +
           "FROM Flight f " +
           "JOIN f.airline a " +
           "JOIN f.route r " +
           "JOIN r.originAirport oa " +
           "JOIN r.destinationAirport da " +
           "WHERE (:number IS NULL OR f.number LIKE CONCAT('%', :number, '%')) " +
           "AND (:airlineName IS NULL OR a.name LIKE CONCAT('%', :airlineName, '%')) " +
           "AND (:originAirportName IS NULL OR oa.name LIKE CONCAT('%', :originAirportName, '%')) " +
           "AND (:destinationAirportName IS NULL OR da.name LIKE CONCAT('%', :destinationAirportName, '%'))")
    Page<FlightProjection> searchFlightsOptimized(
            @Param("number") String number,
            @Param("airlineName") String airlineName,
            @Param("originAirportName") String originAirportName,
            @Param("destinationAirportName") String destinationAirportName,
            Pageable pageable);

    /**
     * Paginated query for all flights with optimized projection
     */
    @Query("SELECT f.flightID as flightID, f.number as number, " +
           "a.airlineID as airlineId, a.name as airlineName, " +
           "r.routeID as routeId, " +
           "oa.Id as originAirportId, oa.name as originAirportName, oa.location as originAirportLocation, " +
           "da.Id as destinationAirportId, da.name as destinationAirportName, da.location as destinationAirportLocation " +
           "FROM Flight f " +
           "JOIN f.airline a " +
           "JOIN f.route r " +
           "JOIN r.originAirport oa " +
           "JOIN r.destinationAirport da")
    Page<FlightProjection> findAllFlightsOptimized(Pageable pageable);
}
