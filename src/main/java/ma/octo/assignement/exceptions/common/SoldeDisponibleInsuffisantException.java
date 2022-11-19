package ma.octo.assignement.exceptions.common;

public class SoldeDisponibleInsuffisantException extends Exception {

  private static final long serialVersionUID = 1L;

  public SoldeDisponibleInsuffisantException() {
  }

  public SoldeDisponibleInsuffisantException(String message) {
    super(message);
  }
}
