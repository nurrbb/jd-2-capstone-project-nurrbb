package io.upschool.dto;

import io.upschool.entity.Airline;
import io.upschool.entity.Route;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class FlightSaveResponse {

    private Long flightID;
    private String number;
    private Airline airline;
    private Route route;
    private Integer totalSeats;
    private Integer availableSeats;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private Integer durationMinutes;
    private BigDecimal basePrice;
}
