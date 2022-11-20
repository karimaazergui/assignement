package ma.octo.assignement.mapper.implimentation;


import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.domain.Account;
import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.mapper.ITransferMapper;
import ma.octo.assignement.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;

@Component
public class  TransferMapper implements ITransferMapper {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Transfer dtoToEntity(TransferDto transferDto) throws AccountNotFoundException, TransactionException {
        Account sender = accountRepository.findByNrCompte(transferDto.getNrCompteEmetteur());
        Account receiver = accountRepository.findByNrCompte(transferDto.getNrCompteBeneficiaire());
        if (sender == null || receiver == null){
            //todo logging
            throw new AccountNotFoundException("Compte non existant");
        }

        return Transfer.builder()
                .compteEmetteur(sender)
                .compteBeneficiaire(receiver)
                .dateExecution(transferDto.getDate())
                .montantTransfer(transferDto.getMontant())
                .motifTransfer(transferDto.getMotif())
                .build();
    }

    @Override
    public TransferDto entityToDto(Transfer transfer) {

        return TransferDto.builder()
                .nrCompteBeneficiaire(transfer.getCompteBeneficiaire().getNrCompte())
                .nrCompteEmetteur(transfer.getCompteEmetteur().getNrCompte())
                .date(transfer.getDateExecution())
                .motif(transfer.getMotifTransfer())
                .montant(transfer.getMontantTransfer())
                .build();
    }
}