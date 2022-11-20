package ma.octo.assignement.mapper.implimentation;

import ma.octo.assignement.dto.DepositDto;
import ma.octo.assignement.domain.Account;
import ma.octo.assignement.domain.Deposit;
import ma.octo.assignement.exceptions.TransactionException;
import ma.octo.assignement.mapper.IDepositMapper;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;
@Component
public class DepositMapper implements IDepositMapper {
    @Override
    public  Deposit dtoToEntity(DepositDto depositDto) throws AccountNotFoundException, TransactionException {
        Account compteBenefiaire = new Account();
        compteBenefiaire.setRib(depositDto.getRibCompteBeneficiaire());

        Deposit deposit = new Deposit();
        deposit.setMontant(depositDto.getMontantVersement());
        deposit.setNomPrenomEmetteur(depositDto.getNomPrenomEmetteur());
        deposit.setCompteBeneficiaire(compteBenefiaire);
        deposit.setMotifDeposit(depositDto.getMotifVersement());
        deposit.setDateExecution(depositDto.getDateExecution());

        return deposit;
    }

    @Override
    public DepositDto entityToDto(Deposit deposit) {
        DepositDto versementDto = new DepositDto();
        versementDto.setMontantVersement(deposit.getMontant());
        versementDto.setNomPrenomEmetteur(deposit.getNomPrenomEmetteur());
        versementDto.setRibCompteBeneficiaire(deposit.getCompteBeneficiaire().getRib());
        versementDto.setMotifVersement(deposit.getMotifDeposit());
        versementDto.setDateExecution(deposit.getDateExecution());

        return versementDto;
    }
}
