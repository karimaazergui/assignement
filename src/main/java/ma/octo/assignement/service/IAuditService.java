package ma.octo.assignement.service;

import ma.octo.assignement.entities.util.EventType;

public interface IAuditService {
    public void audit(String message, EventType auditType);

}
