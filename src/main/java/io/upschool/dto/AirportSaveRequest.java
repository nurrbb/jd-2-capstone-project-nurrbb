package io.upschool.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AirportSaveRequest {

    @NotBlank(message = "Airport name cannot be blank")
    private String name;

    @NotBlank(message = "Airport location cannot be blank")
    private String location;

}
