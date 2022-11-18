package ma.octo.assignement.web;

import ma.octo.assignement.entities.Transfer;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.exceptions.SoldeDisponibleInsuffisantException;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.service.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/transfers")
class TransferController {
    @Autowired
    ITransferService iTransferService;
    @GetMapping("/")
    List<Transfer> loadAll() {
       return iTransferService.loadAllTransfers();
    }
    @PostMapping("/saveTransfers")
    @ResponseStatus(HttpStatus.CREATED)
    public void createTransaction(@RequestBody TransferDto transferDto) throws SoldeDisponibleInsuffisantException, CompteNonExistantException, TransactionException
    {
        iTransferService.createTransaction(transferDto);
    }

}
