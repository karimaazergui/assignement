package ma.octo.assignement.service.implementation;

import ma.octo.assignement.entities.Transfer;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.entities.util.EventType;
import ma.octo.assignement.exceptions.common.CompteNonExistantException;
import ma.octo.assignement.exceptions.common.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.common.TransactionException;
import ma.octo.assignement.exceptions.common.TransferNonExistantException;
import ma.octo.assignement.mapper.ITransferMapper;
import ma.octo.assignement.mapper.implimentation.TransferMapper;
import ma.octo.assignement.repository.AccountRepository;
import ma.octo.assignement.repository.TransferRepository;
import ma.octo.assignement.service.IAuditService;
import ma.octo.assignement.service.ITransferService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
   IAuditService iAuditService;
   @Autowired
   ITransferMapper iTransferMapper;
    public static final int MONTANT_MAXIMAL = 10000;
    public static final int MONTANT_MINIMAL = 10;
    Logger LOGGER = LoggerFactory.getLogger(TransferService.class);
    @Override
    public List<Transfer> allTransfers() {
        LOGGER.info("Liste des Transferts");
        return CollectionUtils.isEmpty(transferRepository.findAll()) ? null :transferRepository.findAll() ;
    }


    @Override
    public void createTransaction(TransferDto transferDto) throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException, AccountNotFoundException {

        Transfer transfer= iTransferMapper.dtoToEntity(transferDto);

        if (transfer.getCompteEmetteur()== null || transfer.getCompteBeneficiaire()== null) {
            LOGGER.error("Compte Non existant");
            throw new CompteNonExistantException("Compte Non existant");
        }

       if (transfer.getMontantTransfer().intValue() < MONTANT_MINIMAL) {
            LOGGER.error("Montant minimal de transfer non atteint");
            throw new TransactionException("Montant minimal de transfer non atteint");
        } else if (transfer.getMontantTransfer().intValue() > MONTANT_MAXIMAL) {
            LOGGER.error("Montant maximal de transfer dépassé");
            throw new TransactionException("Montant maximal de transfer dépassé");
        }

        if (transfer.getCompteEmetteur().getSolde().compareTo(transferDto.getMontant())==-1) {
            LOGGER.error("Solde insuffisant pour l'utilisateur");
            throw new SoldeDisponibleInsuffisantException("Solde insuffisant pour l'utilisateur");
        }


        transfer.getCompteEmetteur().setSolde(transfer.getCompteEmetteur().getSolde().subtract(transferDto.getMontant()));
        compteRepository.save(transfer.getCompteEmetteur());

        transfer.getCompteBeneficiaire().setSolde(new BigDecimal(transfer.getCompteBeneficiaire().getSolde().intValue() + transferDto.getMontant().intValue()));
        compteRepository.save(transfer.getCompteBeneficiaire());

        transferRepository.save(transfer);

        String message="Transfer depuis " + transferDto.getNrCompteEmetteur() + " vers " + transferDto
                .getNrCompteBeneficiaire() + " d'un montant de " + transferDto.getMontant()
                .toString();
        iAuditService.audit(message, EventType.TRANSFER);
    }

    @Override
    public TransferDto getTransfer(Long id) throws TransferNonExistantException {
        return iTransferMapper.entityToDto(transferRepository.findById(id)
                .orElseThrow(()->new TransferNonExistantException("Virement non existant"))
        );
    }
}
