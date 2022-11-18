package ma.octo.assignement.service;

import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.dto.TransferDto;


import java.util.List;

public interface ITransferService {
    List<Transfer> loadAllTransfers();
    public void createTransaction( TransferDto transferDto);
}
