package ma.octo.assignement.service.implementation;

import ma.octo.assignement.dto.DepositDto;
import ma.octo.assignement.domain.Account;
import ma.octo.assignement.domain.Deposit;
import ma.octo.assignement.domain.util.EventType;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.DepositNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.mapper.IDepositMapper;
import ma.octo.assignement.repository.AccountRepository;
import ma.octo.assignement.repository.DepositRepository;
import ma.octo.assignement.service.IAccountService;
import ma.octo.assignement.service.IAuditService;
import ma.octo.assignement.service.IDepositService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
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
    IDepositMapper iDepositMapper;
    @Autowired
    AccountRepository compteRepository;
    @Autowired
    IAuditService iAuditService;
    Logger LOGGER = LoggerFactory.getLogger(DepositService.class);
    @Override
    public List<Deposit> allDeposits() {
        LOGGER.info("Liste des Deposits");
        return depositRepository.findAll() ;
    }

    @Override
    public void createTransaction(DepositDto depositDto) throws CompteNonExistantException, TransactionException, AccountNotFoundException {
        Account compteBeneficiaire = iAccountService.getAccountByRib(depositDto.getRibCompteBeneficiaire());
        if (compteBeneficiaire== null) {
            LOGGER.error("Compte Non existant");
            throw new CompteNonExistantException("Compte Non existant");
        }
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

        Deposit deposit= iDepositMapper.dtoToEntity(depositDto);

        depositRepository.save(deposit);

         String message="depot par " + depositDto.getNomPrenomEmetteur() + " vers " + depositDto
                .getRibCompteBeneficiaire() + " d'un montant de " + depositDto.getMontantVersement()
                .toString();
        iAuditService.audit(message, EventType.DEPOSIT);

    }

    @Override
    public DepositDto getdeposit(Long id) throws DepositNonExistantException {
        return iDepositMapper.entityToDto(depositRepository.findById(id)
                .orElseThrow(()->new DepositNonExistantException("Virement non existant"))
        );
    }
}
