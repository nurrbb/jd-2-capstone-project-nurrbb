package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "airport", indexes = {
    @Index(name = "idx_airport_name", columnList = "name"),
    @Index(name = "idx_airport_location", columnList = "location")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long Id;

    @Column(name= "name", nullable = false,length = 100)
    private String name;

    @Column(name ="location",nullable = false,length = 100)
    private String location;


}
