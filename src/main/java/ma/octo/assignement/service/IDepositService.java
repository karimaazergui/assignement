package ma.octo.assignement.service;

import ma.octo.assignement.dto.DepositDto;
import ma.octo.assignement.domain.Deposit;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.DepositNonExistantException;
import ma.octo.assignement.exceptions.TransactionException;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface IDepositService {
    List<Deposit> allDeposits();
    void createDeposit(DepositDto depositDto) throws CompteNonExistantException, TransactionException, AccountNotFoundException;


}
