package io.upschool.dto;

import io.upschool.entity.Flight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Airlinedto {
    private Long id;
    private String name;

}
