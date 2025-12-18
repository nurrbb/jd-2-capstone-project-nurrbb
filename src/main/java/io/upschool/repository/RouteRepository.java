package io.upschool.repository;

import io.upschool.dto.RouteProjection;
import io.upschool.entity.Airport;
import io.upschool.entity.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface RouteRepository extends JpaRepository<Route,Long> {
    List<Route> findByOriginAirportIdAndDestinationAirportId(Long originAirportId, Long destinationAirportId);
    boolean existsByOriginAirportAndDestinationAirport(Airport originAirport, Airport destinationAirport);

    /**
     * Optimized query with projection - selects only necessary columns
     * Improves performance by avoiding full entity loading
     */
    @Query("SELECT r.routeID as routeID, " +
           "oa.Id as originAirportId, oa.name as originAirportName, oa.location as originAirportLocation, " +
           "da.Id as destinationAirportId, da.name as destinationAirportName, da.location as destinationAirportLocation " +
           "FROM Route r " +
           "JOIN r.originAirport oa " +
           "JOIN r.destinationAirport da " +
           "WHERE oa.Id = :originAirportId AND da.Id = :destinationAirportId")
    List<RouteProjection> findRoutesByAirportsOptimized(
            @Param("originAirportId") Long originAirportId,
            @Param("destinationAirportId") Long destinationAirportId);

    /**
     * Paginated search for routes with optimized projection
     */
    @Query("SELECT r.routeID as routeID, " +
           "oa.Id as originAirportId, oa.name as originAirportName, oa.location as originAirportLocation, " +
           "da.Id as destinationAirportId, da.name as destinationAirportName, da.location as destinationAirportLocation " +
           "FROM Route r " +
           "JOIN r.originAirport oa " +
           "JOIN r.destinationAirport da " +
           "WHERE (:originAirportId IS NULL OR oa.Id = :originAirportId) " +
           "AND (:destinationAirportId IS NULL OR da.Id = :destinationAirportId) " +
           "AND (:originAirportName IS NULL OR oa.name LIKE CONCAT('%', :originAirportName, '%')) " +
           "AND (:destinationAirportName IS NULL OR da.name LIKE CONCAT('%', :destinationAirportName, '%'))")
    Page<RouteProjection> searchRoutesOptimized(
            @Param("originAirportId") Long originAirportId,
            @Param("destinationAirportId") Long destinationAirportId,
            @Param("originAirportName") String originAirportName,
            @Param("destinationAirportName") String destinationAirportName,
            Pageable pageable);

    /**
     * Paginated query for all routes with optimized projection
     */
    @Query("SELECT r.routeID as routeID, " +
           "oa.Id as originAirportId, oa.name as originAirportName, oa.location as originAirportLocation, " +
           "da.Id as destinationAirportId, da.name as destinationAirportName, da.location as destinationAirportLocation " +
           "FROM Route r " +
           "JOIN r.originAirport oa " +
           "JOIN r.destinationAirport da")
    Page<RouteProjection> findAllRoutesOptimized(Pageable pageable);
}
