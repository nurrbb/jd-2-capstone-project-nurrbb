package io.upschool.dto;

import io.upschool.entity.Airline;
import io.upschool.entity.Route;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class FlightSaveResponse {

    private Long flightID;
    private String number;
    private Airline airline;
    private Route route;
}
