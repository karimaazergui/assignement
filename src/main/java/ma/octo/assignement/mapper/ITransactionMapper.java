package ma.octo.assignement.mapper;

import ma.octo.assignement.domain.Transaction;
import ma.octo.assignement.dto.DepositDto;
import ma.octo.assignement.domain.Deposit;

import ma.octo.assignement.dto.TransactionDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;

import javax.security.auth.login.AccountNotFoundException;

public interface ITransactionMapper {
    public  Transaction dtoToEntity(TransactionDto transactionDto) throws CompteNonExistantException;
    public TransactionDto entityToDto(Transaction transaction );
}
