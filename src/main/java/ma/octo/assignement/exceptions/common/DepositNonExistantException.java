package ma.octo.assignement.exceptions.common;

public class DepositNonExistantException extends Exception {

    private static final long serialVersionUID = 1L;

    public DepositNonExistantException() {
    }

    public DepositNonExistantException(String message) {
        super(message);
    }
}
