package ma.octo.assignement.mapper;

import ma.octo.assignement.entities.Transfer;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.common.TransactionException;

import javax.security.auth.login.AccountNotFoundException;

public interface ITransferMapper {
    public Transfer dtoToEntity(TransferDto transferDto) throws AccountNotFoundException, TransactionException;
    public TransferDto entityToDto(Transfer transfer);
        }
