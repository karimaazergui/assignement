package ma.octo.assignement.exceptions.common;

public class CompteNonExistantException extends Exception {

  private static final long serialVersionUID = 1L;

  public CompteNonExistantException() {
  }

  public CompteNonExistantException(String message) {
    super(message);
  }
}
