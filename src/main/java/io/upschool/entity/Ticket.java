package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;

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

    @Column(name = "maskedCreditCardNumber")
    private String maskedCreditCardNumber;

    @Column(name = "seat_number")
    private String seatNumber;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name ="is_active")
    @Builder.Default()
    private boolean active = Boolean.TRUE;
}
