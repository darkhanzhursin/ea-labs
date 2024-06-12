package bank.repository;

import bank.domain.TraceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTraceRepository extends JpaRepository<TraceRecord, Long> {
}
