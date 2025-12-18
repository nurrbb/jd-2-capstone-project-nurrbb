package io.upschool.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name ="Airline", indexes = {
    @Index(name = "idx_airline_name", columnList = "name")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airlineID;

    @Column(name ="name", nullable = false,length = 100)
    private String name;

    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Flight> flights;


    }
