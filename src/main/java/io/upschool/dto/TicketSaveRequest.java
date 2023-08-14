package io.upschool.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketSaveRequest {

    private String ticketNumber;
    private String passengerName;
    private String passengerSurname;
    private Long flightID;
    private String creditCardNumber;

}
