package io.upschool.dto;

import io.upschool.entity.Airport;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RouteSaveRequest {


    private Airport originAirport;
    private Airport destinationAirport;

}
