package ma.octo.assignement.service.implementation;

import ma.octo.assignement.dto.DepositDto;
import ma.octo.assignement.entities.Account;
import ma.octo.assignement.entities.Deposit;
import ma.octo.assignement.entities.util.EventType;
import ma.octo.assignement.exceptions.common.CompteNonExistantException;
import ma.octo.assignement.exceptions.common.TransactionException;
import ma.octo.assignement.repository.AccountRepository;
import ma.octo.assignement.repository.DepositRepository;
import ma.octo.assignement.service.IAccountService;
import ma.octo.assignement.service.IAuditService;
import ma.octo.assignement.service.IDepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
@Service
public class DepositService implements IDepositService {
    public static final int MONTANT_MAXIMAL = 10000;
    public static final int MONTANT_MINIMAL = 10;
    @Autowired
    DepositRepository depositRepository;
    @Autowired
    IAccountService iAccountService;
    @Autowired
    AccountRepository compteRepository;

    @Autowired
    IAuditService iAuditService;
    Logger LOGGER = LoggerFactory.getLogger(TransferService.class);
    @Override
    public List<Deposit> allDeposits() {
        LOGGER.info("Liste des Deposits");
        return CollectionUtils.isEmpty(depositRepository.findAll()) ? null :depositRepository.findAll() ;
    }

    @Override
    public void createTransaction(DepositDto depositDto) throws CompteNonExistantException, TransactionException {
        Account compteBeneficiaire = iAccountService.getCompteByRib(depositDto.getRibCompteBeneficiaire());
        if(depositDto.getMontantVersement() == null ||
                depositDto.getMontantVersement().compareTo(BigDecimal.ZERO) == 0) {
            LOGGER.error("Montant vide");
            throw new TransactionException("Montant vide");
        } else if(depositDto.getMontantVersement().intValue() < MONTANT_MINIMAL ) {
            LOGGER.error("Montant minimal de virement non atteint");
            throw new TransactionException("Montant minimal de virement non atteint");
        } else if (depositDto.getMontantVersement().intValue() > MONTANT_MAXIMAL) {
            LOGGER.error("Montant maximal de virement dépassé");
            throw new TransactionException("Montant maximal de virement dépassé");
        }

        compteBeneficiaire.setSolde(compteBeneficiaire.getSolde().add(depositDto.getMontantVersement()));
        compteRepository.save(compteBeneficiaire);

        Deposit deposit= new Deposit();
        deposit.setMontant(depositDto.getMontantVersement());
        deposit.setCompteBeneficiaire(compteBeneficiaire);
        deposit.setMotifDeposit(depositDto.getMotifVersement());
        deposit.setNomPrenomEmetteur(depositDto.getNomPrenomEmetteur());
        deposit.setDateExecution(depositDto.getDateExecution());

        depositRepository.save(deposit);

         String message="depot par " + depositDto.getNomPrenomEmetteur() + " vers " + depositDto
                .getRibCompteBeneficiaire() + " d'un montant de " + depositDto.getMontantVersement()
                .toString();
        iAuditService.audit(message, EventType.DEPOSIT);




    }
}
