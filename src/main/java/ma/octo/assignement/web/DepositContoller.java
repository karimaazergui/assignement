package ma.octo.assignement.web;

import ma.octo.assignement.dto.DepositDto;
import ma.octo.assignement.domain.Deposit;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.service.IDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController(value = "/deposits")
public class DepositContoller {
    @Autowired
    IDepositService iDepositService;
    @GetMapping("/")
    List<Deposit> allDeposits() {
        return iDepositService.allDeposits();
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@RequestBody DepositDto depositDto) throws TransactionException, CompteNonExistantException, SoldeDisponibleInsuffisantException, AccountNotFoundException {
        iDepositService.createTransaction(depositDto);
    }

}
