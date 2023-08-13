package io.upschool.dto;

import io.upschool.entity.Airport;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class RouteSaveDto {

    private Airport originAirport;

    private Airport destinationAirport;

}
