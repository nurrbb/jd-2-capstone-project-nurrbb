package io.upschool.dto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketSaveRequest {

    @NotBlank(message = "Passenger name cannot be blank")
    @Size(min = 2, max = 50, message = "Passenger name must be between 2 and 50 characters")
    private String passengerName;

    @NotBlank(message = "Passenger surname cannot be blank")
    @Size(min = 2, max = 50, message = "Passenger surname must be between 2 and 50 characters")
    private String passengerSurname;

    @NotNull(message = "Flight ID cannot be null")
    private Long flightID;

    @NotBlank(message = "Credit card number cannot be blank")
    @Pattern(regexp = "\\d{16}", message = "Credit card number must be exactly 16 digits")
    private String creditCardNumber;

    @NotBlank(message = "Seat number cannot be blank")
    @Pattern(regexp = "[A-Z]\\d{1,2}", message = "Seat number must be in format like A1, B12")
    private String seatNumber;
}
