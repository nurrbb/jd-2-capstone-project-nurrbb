package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "ticket")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "is_active = true")

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ticketID;

   @Column(name= "ticketNumber")
    private String ticketNumber;

    @Column(name= "passengerName",nullable = false)
    private String passengerName;

    @Column(name= "passengerSurname",nullable = false)
    private String passengerSurname;

    @ManyToOne
    @JoinColumn(name = "flight_id",nullable = false)
    private Flight flight;

    @Column(name ="is active")
    private Boolean active;
}
