package io.upschool.repository;

import io.upschool.entity.Airline;
import io.upschool.entity.Ticket;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    List<Ticket> findAllByTicketNumberIs(String TicketNumber);
}
