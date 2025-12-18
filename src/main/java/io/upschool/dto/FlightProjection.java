package io.upschool.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Projection interface for optimized Flight queries
 * Selects only necessary fields to improve query performance
 */
public interface FlightProjection {
    Long getFlightID();
    String getNumber();
    Long getAirlineId();
    String getAirlineName();
    Long getRouteId();
    Long getOriginAirportId();
    String getOriginAirportName();
    String getOriginAirportLocation();
    Long getDestinationAirportId();
    String getDestinationAirportName();
    String getDestinationAirportLocation();
    Integer getTotalSeats();
    Integer getAvailableSeats();
    LocalDateTime getDepartureTime();
    LocalDateTime getArrivalTime();
    Integer getDurationMinutes();
    BigDecimal getBasePrice();
}

