package ma.octo.assignement.service;

import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.repository.TransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
@Service
public class TransferService implements ITransferService{
    @Autowired
    TransferRepository transferRepository;
    Logger LOGGER = LoggerFactory.getLogger(AuditService.class);
    @Override
    public List<Transfer> loadAllTransfers() {
        LOGGER.info("Liste des Transferts");
        return CollectionUtils.isEmpty(transferRepository.findAll()) ? null :transferRepository.findAll() ;
    }

    @Override
    public void createTransaction(TransferDto transferDto) {

    }
}
