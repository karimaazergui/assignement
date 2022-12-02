package ma.octo.assignement.service;

import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.exceptions.TransferNonExistantException;


import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface ITransferService {
    List<Transfer> allTransfers();
    public void createTransfer (TransferDto transferDto) throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException, AccountNotFoundException;

}
