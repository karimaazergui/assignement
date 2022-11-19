package ma.octo.assignement.repository;

import ma.octo.assignement.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
  Account findByNrCompte(String nrCompte);
  Account findByRib(String rib);
}
