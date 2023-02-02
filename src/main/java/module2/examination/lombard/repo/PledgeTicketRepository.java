package module2.examination.lombard.repo;

import module2.examination.lombard.models.PledgeTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PledgeTicketRepository extends JpaRepository<PledgeTicket, Long> {
}