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

public class AirlineSaveRequest {
    @NotBlank(message = "Airline name cannot be blank")
    private String name;
}
