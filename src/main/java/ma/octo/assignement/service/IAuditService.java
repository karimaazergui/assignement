package ma.octo.assignement.service;

import ma.octo.assignement.domain.util.EventType;

public interface IAuditService {
    public void audit(String message, EventType auditType);

}
