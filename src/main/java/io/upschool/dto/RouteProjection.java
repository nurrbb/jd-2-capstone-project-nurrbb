package io.upschool.dto;

/**
 * Projection interface for optimized Route queries
 * Selects only necessary fields to improve query performance
 */
public interface RouteProjection {
    Long getRouteID();
    Long getOriginAirportId();
    String getOriginAirportName();
    String getOriginAirportLocation();
    Long getDestinationAirportId();
    String getDestinationAirportName();
    String getDestinationAirportLocation();
}

