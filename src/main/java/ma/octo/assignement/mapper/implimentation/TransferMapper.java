package ma.octo.assignement.mapper.implimentation;

import ma.octo.assignement.domain.Account;
import ma.octo.assignement.domain.Transaction;
import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.dto.TransactionDto;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.mapper.ITransactionMapper;
import ma.octo.assignement.repository.AccountRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
@Service

public class TransferMapper extends TransactionMapper implements ITransactionMapper {
    @Autowired
    AccountRepository  accountRepository;
    @Override
    public Transaction dtoToEntity(TransactionDto transactionDto) throws AccountNotFoundException {
        Transfer transfer=new Transfer();
        BeanUtils.copyProperties(super.dtoToEntity(transactionDto),transfer);
        Account emetteur=accountRepository.findByNrCompte(((TransferDto)transactionDto).getNrCompteEmetteur());
        if (emetteur == null ){
            throw new AccountNotFoundException("Compte non existant");
        }
        transfer.setCompteEmetteur(emetteur);

        return transfer;

    }

    @Override
    public TransactionDto entityToDto(Transaction transaction) {
        TransferDto transferDto=new TransferDto();
        BeanUtils.copyProperties(super.entityToDto(transaction),transferDto);
        transferDto.setNrCompteEmetteur(((Transfer)transaction).getCompteEmetteur().getNrCompte());
        return transferDto;
    }
}
