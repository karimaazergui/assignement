package ma.octo.assignement.mapper.implimentation;

import ma.octo.assignement.domain.Transaction;
import ma.octo.assignement.domain.Account;
import ma.octo.assignement.dto.TransactionDto;
import ma.octo.assignement.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;

public  class TransactionMapper  {

    @Autowired
    AccountRepository accountRepository;
    public Transaction dtoToEntity(TransactionDto transactionDto) throws AccountNotFoundException {

        Account recepteur = accountRepository.findByNrCompte(transactionDto.getNrCompteBeneficiaire());
        if ( recepteur == null){
            throw new AccountNotFoundException("Compte recepteur non existant");
        }
        Transaction transaction = new Transaction();
        transaction.setCompteBeneficiaire(recepteur);
        transaction.setMontant(transactionDto.getMontant());
        transaction.setDateExecution(transactionDto.getDate());
        transaction.setMotif(transactionDto.getMotif());

        return transaction;

    }


    public TransactionDto entityToDto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setDate(transaction.getDateExecution());
        transactionDto.setNrCompteBeneficiaire(transaction.getCompteBeneficiaire().getNrCompte());
        transactionDto.setMontant(transaction.getMontant());
        transactionDto.setMotif(transaction.getMotif());
        return transactionDto;
    }
}
