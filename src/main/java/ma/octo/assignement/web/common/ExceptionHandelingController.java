package ma.octo.assignement.web.common;



import ma.octo.assignement.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ExceptionHandelingController {

    @ExceptionHandler(SoldeDisponibleInsuffisantException.class)
    public ResponseEntity<String> handleSoldeDisponibleInsuffisantException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Pas de solde pas de transfer", null, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(CompteNonExistantException.class)
    public ResponseEntity<String> handleCompteNonExistantException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Compte introuvable", null, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<String> handleTransactionException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Transaction erreur", null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransferNonExistantException.class)
    public ResponseEntity<String> handleVersementNonExistantException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Versement introuvable", null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DepositNonExistantException.class)
    public ResponseEntity<String> handleVirementNonExistantException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("Virement introuvable", null, HttpStatus.NOT_FOUND);
    }
}
