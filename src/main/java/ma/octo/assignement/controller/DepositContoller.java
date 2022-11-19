package ma.octo.assignement.controller;

import ma.octo.assignement.dto.DepositDto;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.entities.Deposit;
import ma.octo.assignement.entities.Transfer;
import ma.octo.assignement.exceptions.common.CompteNonExistantException;
import ma.octo.assignement.exceptions.common.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.common.TransactionException;
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
    @PostMapping("/saveDeposits")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@RequestBody DepositDto depositDto) throws TransactionException, CompteNonExistantException, SoldeDisponibleInsuffisantException, AccountNotFoundException {
        iDepositService.createTransaction(depositDto);
    }

}
