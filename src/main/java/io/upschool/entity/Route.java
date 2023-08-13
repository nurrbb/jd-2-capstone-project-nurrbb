package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "route")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeID;

    @ManyToOne
    @JoinColumn(name = "origin_airport_location")
    private Airport originAirport;

    @ManyToOne
    @JoinColumn(name = "destination_airport_location")
    private Airport destinationAirport;


}
