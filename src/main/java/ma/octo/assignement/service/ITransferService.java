package ma.octo.assignement.service;

import ma.octo.assignement.entities.Transfer;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;


import java.util.List;

public interface ITransferService {
    List<Transfer> loadAllTransfers();
    public void createTransaction (TransferDto transferDto)throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException;

    void save(Transfer transfer);
}
