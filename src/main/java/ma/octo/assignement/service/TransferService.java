package ma.octo.assignement.service;

import ma.octo.assignement.entities.Compte;
import ma.octo.assignement.entities.Transfer;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.TransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
@Service
public class TransferService implements ITransferService{
    @Autowired
    TransferRepository transferRepository;
    @Autowired
    CompteRepository compteRepository;

    public static final int MONTANT_MAXIMAL = 10000;
    Logger LOGGER = LoggerFactory.getLogger(TransferService.class);
    @Override
    public List<Transfer> loadAllTransfers() {
        LOGGER.info("Liste des Transferts");
        return CollectionUtils.isEmpty(transferRepository.findAll()) ? null :transferRepository.findAll() ;
    }

    @Override
    public void save(Transfer transfer) {
        transferRepository.save(transfer);
    }

    @Override
    public void createTransaction(TransferDto transferDto) throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException {
        Compte compte1 = compteRepository.findByNrCompte(transferDto.getNrCompteEmetteur());
        Compte compte2= compteRepository.findByNrCompte(transferDto.getNrCompteBeneficiaire());

        if (compte1 == null) {
            System.out.println("Compte Non existant");
            throw new CompteNonExistantException("Compte Non existant");
        }

        if (compte2 == null) {
            System.out.println("Compte Non existant");
            throw new CompteNonExistantException("Compte Non existant");
        }
/*
        if (transferDto.getMontant().intValue() == 0) {
            System.out.println("Montant vide");
            throw new TransactionException("Montant vide");
        } else if (transferDto.getMontant().intValue() < 10) {
            System.out.println("Montant minimal de transfer non atteint");
            throw new TransactionException("Montant minimal de transfer non atteint");
        } else if (transferDto.getMontant().intValue() > MONTANT_MAXIMAL) {
            System.out.println("Montant maximal de transfer dépassé");
            throw new TransactionException("Montant maximal de transfer dépassé");
        }

        if (transferDto.getMotif().length() < 0) {
            System.out.println("Motif vide");
            throw new TransactionException("Motif vide");
        }

        if (c1.getSolde().intValue() - transferDto.getMontant().intValue() < 0) {
            LOGGER.error("Solde insuffisant pour l'utilisateur");
        }

        if (c1.getSolde().intValue() - transferDto.getMontant().intValue() < 0) {
            LOGGER.error("Solde insuffisant pour l'utilisateur");
        }

        c1.setSolde(c1.getSolde().subtract(transferDto.getMontant()));
        rep1.save(c1);

        f12
                .setSolde(new BigDecimal(f12.getSolde().intValue() + transferDto.getMontant().intValue()));
        rep1.save(f12);

        Transfer transfer = new Transfer();
        transfer.setDateExecution(transferDto.getDate());
        transfer.setCompteBeneficiaire(f12);
        transfer.setCompteEmetteur(c1);
        transfer.setMontantTransfer(transferDto.getMontant());

        re2.save(transfer);

        monservice.auditTransfer("Transfer depuis " + transferDto.getNrCompteEmetteur() + " vers " + transferDto
                        .getNrCompteBeneficiaire() + " d'un montant de " + transferDto.getMontant()
                        .toString());*/
    }

}
