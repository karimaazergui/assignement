package ma.octo.assignement.service;

import ma.octo.assignement.domain.Deposit;
import ma.octo.assignement.domain.Transaction;
import ma.octo.assignement.dto.TransactionDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;

import javax.security.auth.login.AccountNotFoundException;

public interface ITransactionService {
    void createTransaction(Transaction transaction) throws CompteNonExistantException, TransactionException, AccountNotFoundException;
}
