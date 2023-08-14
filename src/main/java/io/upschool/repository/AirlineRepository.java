package io.upschool.repository;

import io.upschool.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirlineRepository extends JpaRepository<Airline,Long> {
    List<Airline> findAllByNameIs(String Name);
    boolean existsByName(String name);
}
