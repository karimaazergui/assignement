package ma.octo.assignement.controller;

import ma.octo.assignement.entities.Transfer;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.common.CompteNonExistantException;
import ma.octo.assignement.exceptions.common.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.common.TransactionException;
import ma.octo.assignement.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController(value = "/transfers")
class TransferController {
    @Autowired
    ITransferService iTransferService;
    @GetMapping("/")
    List<Transfer> allTransfers() {
       return iTransferService.allTransfers();
    }
    @PostMapping("/saveTransfers")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@RequestBody TransferDto transferDto) throws TransactionException, CompteNonExistantException, SoldeDisponibleInsuffisantException, AccountNotFoundException {
        iTransferService.createTransaction(transferDto);
    }

}
