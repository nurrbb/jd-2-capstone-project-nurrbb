package io.upschool.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name ="flight", indexes = {
    @Index(name = "idx_flight_number", columnList = "number"),
    @Index(name = "idx_flight_airline_id", columnList = "airline_id"),
    @Index(name = "idx_flight_route_id", columnList = "route_id")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightID;

    @Column(name = "number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "airline_id",nullable = false)
    private Airline airline;

    @ManyToOne
    @JoinColumn(name = "route_id",nullable = false)
    private Route route;

    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Column(name = "base_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;
}
