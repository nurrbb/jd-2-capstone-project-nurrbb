package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketID;

    @Column(name= "ticketNumber")
    private String ticketNumber;

    @Column(name= "passengerName")
    private String passengerName;

    @Column(name= "passengerSurname")
    private String passengerSurname;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
}
