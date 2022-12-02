package ma.octo.assignement.service.implementation;

import ma.octo.assignement.dto.DepositDto;
import ma.octo.assignement.domain.Account;
import ma.octo.assignement.domain.Deposit;
import ma.octo.assignement.domain.util.EventType;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.DepositNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.mapper.ITransactionMapper;
import ma.octo.assignement.repository.AccountRepository;
import ma.octo.assignement.repository.DepositRepository;
import ma.octo.assignement.service.IAccountService;
import ma.octo.assignement.service.IAuditService;
import ma.octo.assignement.service.IDepositService;
import ma.octo.assignement.service.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
@Service
public class DepositService implements IDepositService {
    @Autowired
    DepositRepository depositRepository;
    @Autowired
    @Qualifier(value = "depositMapper")
    private ITransactionMapper depositMapper;
    @Autowired
    IAuditService auditService;
    @Autowired
    private ITransactionService transactionService;


    @Override
    public List<Deposit> allDeposits() {
        return depositRepository.findAll() ;
    }

    @Override
    public void createDeposit(DepositDto depositDto) throws CompteNonExistantException, TransactionException, AccountNotFoundException {
/*
        Date today= new Date();

       if(depositRepository.countByCompteBeneficiaireAndDateExecution(compteBeneficiaire,today)>TRANSAC_MAX){

            LOGGER.error("transaction max");
            throw new TransactionException("transaction max");
        }*/

        Deposit deposit = (Deposit) depositMapper.dtoToEntity(depositDto);
        transactionService.createTransaction(deposit);
        depositRepository.save(deposit);

         String message="depot par " + deposit.getNomPrenomEmetteur() + " vers " + deposit
                .getCompteBeneficiaire().getNrCompte() + " d'un montant de " + deposit.getMontant()
                .toString();
        auditService.audit(message, EventType.DEPOSIT);

    }


}
