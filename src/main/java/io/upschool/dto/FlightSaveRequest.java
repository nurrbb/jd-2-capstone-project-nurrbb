package io.upschool.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightSaveRequest {

    @NotBlank(message = "Flight number cannot be blank")
    @Size(min = 2, max = 10, message = "Flight number must be between 2 and 10 characters")
    private String number;

    @NotNull(message = "Airline ID cannot be null")
    private Long airlineId;

    @NotNull(message = "Route ID cannot be null")
    private Long routeId;

    @NotNull(message = "Total seats cannot be null")
    @Min(value = 1, message = "Total seats must be at least 1")
    @Max(value = 1000, message = "Total seats cannot exceed 1000")
    private Integer totalSeats;

    @NotNull(message = "Departure time cannot be null")
    @Future(message = "Departure time must be in the future")
    private LocalDateTime departureTime;

    @NotNull(message = "Arrival time cannot be null")
    private LocalDateTime arrivalTime;

    @NotNull(message = "Base price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Base price must be greater than 0")
    private BigDecimal basePrice;
}
