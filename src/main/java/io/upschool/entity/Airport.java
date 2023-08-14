package io.upschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "airport")
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
