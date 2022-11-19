package ma.octo.assignement.service;

import ma.octo.assignement.entities.Transfer;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.common.CompteNonExistantException;
import ma.octo.assignement.exceptions.common.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.common.TransactionException;


import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

public interface ITransferService {
    List<Transfer> allTransfers();
    public void createTransaction (TransferDto transferDto) throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException, AccountNotFoundException;


}
