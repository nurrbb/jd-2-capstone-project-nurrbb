package io.upschool.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSaveRequest {

    @NotNull(message = "Origin airport ID cannot be null")
    private Long originAirportId;

    @NotNull(message = "Destination airport ID cannot be null")
    private Long destinationAirportId;

    @Min(value = 1, message = "Average duration must be at least 1 minute")
    @Max(value = 1440, message = "Average duration cannot exceed 1440 minutes (24 hours)")
    private Integer averageDurationMinutes;

}

