package io.upschool.dto;

import io.upschool.entity.Flight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketSaveResponse {

    private Long ticketId;
    private String ticketNumber;
    private String passengerName;
    private String passengerSurname;
    private Flight flight;
    private String maskedCreditCardNumber;
    private String seatNumber;
    private BigDecimal price;
}
