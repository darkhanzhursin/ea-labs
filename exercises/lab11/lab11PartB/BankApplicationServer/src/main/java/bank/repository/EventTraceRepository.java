package bank.repository;

import bank.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTraceRepository extends JpaRepository<Event, Long> {
}
