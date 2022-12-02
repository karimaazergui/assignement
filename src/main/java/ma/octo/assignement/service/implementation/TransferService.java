package ma.octo.assignement.service.implementation;

import ma.octo.assignement.domain.Account;
import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.domain.util.EventType;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.exceptions.TransferNonExistantException;
import ma.octo.assignement.mapper.ITransactionMapper;
import ma.octo.assignement.repository.AccountRepository;
import ma.octo.assignement.repository.TransferRepository;
import ma.octo.assignement.service.ITransactionService;
import ma.octo.assignement.service.ITransferService;
import ma.octo.assignement.service.IAuditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.List;
@Service
public class TransferService implements ITransferService {
    @Autowired
    TransferRepository transferRepository;
    @Autowired
    AccountRepository compteRepository;
   @Autowired
   IAuditService auditService;

    @Autowired
    private ITransactionService transactionService;

    @Autowired
    @Qualifier(value = "transferMapper")
    private ITransactionMapper transferMapper;



    Logger LOGGER = LoggerFactory.getLogger(TransferService.class);
    @Override
    public List<Transfer> allTransfers() {
        LOGGER.info("Liste des Transferts");
        return transferRepository.findAll() ;
    }


    @Override
    public void createTransfer(TransferDto transferDto) throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException, AccountNotFoundException {

        Transfer transfer = (Transfer) transferMapper.dtoToEntity(transferDto);
        Account emetteur = transfer.getCompteEmetteur();


        if (emetteur== null ) {
            LOGGER.error("Compte emetteur Non existant");
            throw new CompteNonExistantException("Compte emetteur Non existant");}

        if (emetteur.getSolde().compareTo(transferDto.getMontant())==-1) {
            LOGGER.error("Solde insuffisant pour l'utilisateur");
            throw new SoldeDisponibleInsuffisantException("Solde insuffisant pour l'utilisateur");
        }


        transactionService.createTransaction(transfer);
        emetteur.setSolde(emetteur.getSolde().subtract(transferDto.getMontant()));
        compteRepository.save(emetteur);
        transferRepository.save(transfer);

        String message="Transfer depuis " + emetteur+ " vers " + transferDto
                .getNrCompteBeneficiaire() + " d'un montant de " + transferDto.getMontant()
                .toString();
        auditService.audit(message, EventType.TRANSFER);
    }


}
