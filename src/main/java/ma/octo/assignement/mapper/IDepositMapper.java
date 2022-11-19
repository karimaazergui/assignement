package ma.octo.assignement.mapper;

import ma.octo.assignement.dto.DepositDto;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.entities.Deposit;

import ma.octo.assignement.exceptions.common.TransactionException;

import javax.security.auth.login.AccountNotFoundException;

public interface IDepositMapper {
    public  Deposit dtoToEntity(DepositDto depositDto) throws AccountNotFoundException, TransactionException;
    public DepositDto entityToDto(Deposit deposit );
}
