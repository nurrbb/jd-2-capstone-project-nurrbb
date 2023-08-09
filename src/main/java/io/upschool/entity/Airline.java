package io.upschool.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name ="Airline")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airlineID;

    @Column(name ="name")
    private String name;

    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Flight> flights;


    }
