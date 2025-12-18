package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "route", indexes = {
    @Index(name = "idx_route_origin_airport_id", columnList = "origin_airport_id"),
    @Index(name = "idx_route_destination_airport_id", columnList = "destination_airport_id"),
    @Index(name = "idx_route_origin_destination", columnList = "origin_airport_id,destination_airport_id")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeID;

    @ManyToOne
    @JoinColumn(name = "origin_airport_id",nullable = false)
    private Airport originAirport;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id",nullable = false)
    private Airport destinationAirport;

    @Column(name = "average_duration_minutes")
    private Integer averageDurationMinutes;

}
