package io.upschool.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="flight")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightID;

    private String number;

    @ManyToOne
    @JoinColumn(name = "airline_id",nullable = false)
    private Airline airline;

    @ManyToOne
    @JoinColumn(name = "route_id",nullable = false)
    private Route route;
}
