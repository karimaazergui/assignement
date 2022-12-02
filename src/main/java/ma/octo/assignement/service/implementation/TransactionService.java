package ma.octo.assignement.service.implementation;

import ma.octo.assignement.domain.Account;
import ma.octo.assignement.domain.Deposit;
import ma.octo.assignement.domain.Transaction;
import ma.octo.assignement.dto.TransactionDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.mapper.ITransactionMapper;
import ma.octo.assignement.repository.AccountRepository;
import ma.octo.assignement.service.ITransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
@Service
public class TransactionService implements ITransactionService {

    @Autowired
    AccountRepository  accountRepository;
    public static final int MONTANT_MAXIMAL = 10000;
    public static final int MONTANT_MINIMAL = 10;
    Logger LOGGER = LoggerFactory.getLogger(DepositService.class);
    @Override
    public void createTransaction(Transaction transaction) throws CompteNonExistantException, TransactionException, AccountNotFoundException {

        Account recepteur=transaction.getCompteBeneficiaire();
        if (recepteur== null) {
            LOGGER.error("Compte recepteur Non existant");
            throw new CompteNonExistantException("Compte Non existant");
        }
        if (transaction.getMontant().intValue() < MONTANT_MINIMAL) {
            LOGGER.error("Montant minimal de transfer non atteint");
            throw new TransactionException("Montant minimal de transfer non atteint");
        }
        if (transaction.getMontant().intValue() > MONTANT_MAXIMAL) {
            LOGGER.error("Montant maximal de transfer dépassé");
            throw new TransactionException("Montant maximal de transfer dépassé");
        }
        if (transaction.getMotif() == null) {
            throw new TransactionException("Motif vide");
        }
        recepteur.setSolde(recepteur.getSolde().add(transaction.getMontant()));
        accountRepository.save(recepteur);

    }
}
