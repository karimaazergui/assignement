package ma.octo.assignement.repository;

import ma.octo.assignement.domain.Account;
import ma.octo.assignement.domain.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface DepositRepository extends JpaRepository<Deposit, Long> {
    int countByCompteBeneficiaireAndDateExecution(Account account,Date  date);
}
