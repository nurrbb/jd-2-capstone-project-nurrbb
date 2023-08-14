package io.upschool.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSaveRequest {

    private Long originAirportId;
    private Long destinationAirportId;

    public void setOriginAirportId(Long originAirportId) {
        if (originAirportId != null && originAirportId.equals(this.destinationAirportId)) {
            throw new IllegalArgumentException("Origin and destination airports cannot be the same.");
        }
        this.originAirportId = originAirportId;
    }

    public void setDestinationAirportId(Long destinationAirportId) {
        if (destinationAirportId != null && destinationAirportId.equals(this.originAirportId)) {
            throw new IllegalArgumentException("Origin and destination airports cannot be the same.");
        }
        this.destinationAirportId = destinationAirportId;
    }
}

