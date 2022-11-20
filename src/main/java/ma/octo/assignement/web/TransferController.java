package ma.octo.assignement.web;

import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/transfers")
class TransferController {
    @Autowired
    ITransferService iTransferService;
    @GetMapping("")
    List<Transfer> allTransfers() {
       return iTransferService.allTransfers();
    }
    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@RequestBody TransferDto transferDto) throws TransactionException, CompteNonExistantException, SoldeDisponibleInsuffisantException, AccountNotFoundException {
        iTransferService.createTransaction(transferDto);
    }

}
