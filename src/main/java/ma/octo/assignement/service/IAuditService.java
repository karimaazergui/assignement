package ma.octo.assignement.service;

public interface IAuditService {
    public void auditTransfer(String message);
    public void auditDeposit(String message);
}
