package io.upschool.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSaveRequest {

    private Long originAirportId;
    private Long destinationAirportId;

}

