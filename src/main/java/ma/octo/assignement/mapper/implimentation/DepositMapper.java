package ma.octo.assignement.mapper.implimentation;

import ma.octo.assignement.domain.Deposit;
import ma.octo.assignement.domain.Transaction;
import ma.octo.assignement.dto.DepositDto;
import ma.octo.assignement.dto.TransactionDto;
import ma.octo.assignement.mapper.ITransactionMapper;
import org.springframework.beans.BeanUtils;

import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
@Service

public class DepositMapper extends TransactionMapper implements ITransactionMapper {
    @Override
    public Transaction dtoToEntity(TransactionDto transactionDto) throws AccountNotFoundException {

        Deposit deposit = new Deposit();
        BeanUtils.copyProperties(super.dtoToEntity(transactionDto),deposit);
        deposit.setNomPrenomEmetteur(((DepositDto)transactionDto).getNomPrenomEmetteur());
        return deposit;

    }

    @Override
    public TransactionDto entityToDto(Transaction transaction) {
        DepositDto depositDto=new DepositDto();
        BeanUtils.copyProperties(super.entityToDto(transaction),depositDto);
        depositDto.setNomPrenomEmetteur(((Deposit)transaction).getNomPrenomEmetteur());
        return depositDto;
    }
}
