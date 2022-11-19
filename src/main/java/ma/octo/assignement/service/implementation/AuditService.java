package ma.octo.assignement.service.implementation;

import ma.octo.assignement.entities.Audit;
import ma.octo.assignement.entities.util.EventType;
import ma.octo.assignement.repository.AuditTransferRepository;
import ma.octo.assignement.service.IAuditService;
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

    public void audit(String message,EventType auditType) {

        LOGGER.info("Audit de l'événement {}", auditType);

        Audit audit = new Audit();
        audit.setEventType(auditType);
        audit.setMessage(message);
        auditTransferRepository.save(audit);
    }


}
