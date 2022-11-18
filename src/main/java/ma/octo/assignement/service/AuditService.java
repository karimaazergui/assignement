package ma.octo.assignement.service;

import ma.octo.assignement.domain.Audit;
import ma.octo.assignement.domain.util.EventType;
import ma.octo.assignement.repository.AuditTransferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuditService implements IAuditService {

    Logger LOGGER = LoggerFactory.getLogger(AuditService.class);

    @Autowired
    private AuditTransferRepository auditTransferRepository;

    public void auditTransfer(String message) {

        LOGGER.info("Audit de l'événement {}", EventType.TRANSFER);

        Audit audit = new Audit();
        audit.setEventType(EventType.TRANSFER);
        audit.setMessage(message);
        auditTransferRepository.save(audit);
    }


    public void auditDeposit(String message) {

        LOGGER.info("Audit de l'événement {}", EventType.DEPOSIT);

        Audit audit = new Audit();
        audit.setEventType(EventType.DEPOSIT);
        audit.setMessage(message);
        auditTransferRepository.save(audit);
    }
}
