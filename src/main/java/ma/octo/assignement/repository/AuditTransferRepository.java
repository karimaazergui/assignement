package ma.octo.assignement.repository;

import ma.octo.assignement.entities.Audit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditTransferRepository extends JpaRepository<Audit, Long> {
}
