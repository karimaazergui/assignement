package ma.octo.assignement.service;

import ma.octo.assignement.dto.DepositDto;
import ma.octo.assignement.entities.Deposit;
import ma.octo.assignement.exceptions.common.CompteNonExistantException;
import ma.octo.assignement.exceptions.common.TransactionException;

import java.util.List;

public interface IDepositService {
    List<Deposit> allDeposits();
    void createTransaction(DepositDto depositDto) throws CompteNonExistantException, TransactionException;

}
