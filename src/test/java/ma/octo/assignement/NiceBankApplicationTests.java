package ma.octo.assignement;

import ma.octo.assignement.domain.Deposit;
import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.dto.DepositDto;
import ma.octo.assignement.dto.TransferDto;
import ma.octo.assignement.exceptions.CompteNonExistantException;
import ma.octo.assignement.mapper.implimentation.DepositMapper;
import ma.octo.assignement.mapper.implimentation.TransactionMapper;
import ma.octo.assignement.mapper.implimentation.TransferMapper;
import ma.octo.assignement.repository.AccountRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class NiceBankApplicationTests {

	@InjectMocks
	private DepositMapper depositMapper;
	@InjectMocks
	TransferMapper transferMapper;
	@InjectMocks
	TransactionMapper transactionMapper;
	@Mock
	private AccountRepository accountRepository;


	@Test
	void EntityToDtoDeposit() {

		Deposit deposit = CreationDesObjets.getDepositInstance();
		DepositDto result = (DepositDto) depositMapper.entityToDto(deposit);

		assertEquals("karima azergui", result.getNomPrenomEmetteur());
		assertEquals(BigDecimal.TEN, result.getMontant());
		assertEquals("motif 1", result.getMotif());
		assertEquals("account nbr1", result.getNrCompteBeneficiaire());


	}

	@Test
	void DtoToEntityDeposit() throws CompteNonExistantException, AccountNotFoundException {
		DepositDto depositDto = CreationDesObjets.getDepositDtoInstance();
		//compteRepository.save(getCompteInstance());
		when(accountRepository.findByNrCompte(CreationDesObjets.getCompteInstance().getNrCompte())).thenReturn(CreationDesObjets.getCompteInstance());
		Deposit result = (Deposit) depositMapper.dtoToEntity(depositDto);

		assertEquals("karima azergui", result.getNomPrenomEmetteur());
		assertEquals(BigDecimal.TEN, result.getMontant());
		assertEquals("motif 1", result.getMotif());
		assertEquals("account nbr1", result.getCompteBeneficiaire().getNrCompte());


	}

	@Test
	void dtoToEntityDepositWillThrowExceptionWhenRecepteurIsNull() throws CompteNonExistantException {
		DepositDto depositDto = CreationDesObjets.getDepositDtoInstance();

		when(accountRepository.findByNrCompte(CreationDesObjets.getCompteInstance().getNrCompte())).thenReturn(null);

		//Deposit deposit= (Deposit) depositMapper.dtoToEntity(depositDto);

		assertThrows(CompteNonExistantException.class, () -> depositMapper.dtoToEntity(depositDto));

	}
	@Test
	void dtoToEntityTransfer() throws CompteNonExistantException {
		TransferDto transferDto = CreationDesObjets.getTransferDtoInstance();
		when(accountRepository.findByNrCompte(transferDto.getNrCompteEmetteur())).thenReturn(CreationDesObjets.getCompteInstance1());
		when(accountRepository.findByNrCompte(transferDto.getNrCompteBeneficiaire())).thenReturn(CreationDesObjets.getCompteInstance());
		Transfer result = (Transfer) transferMapper.dtoToEntity(transferDto);


		assertEquals(CreationDesObjets.getCompteInstance1().getUtilisateur().getUsername(), result.getCompteEmetteur().getUtilisateur().getUsername());
		assertEquals(BigDecimal.valueOf(2344), result.getMontant());
		assertEquals("g", result.getMotif());
		assertEquals(CreationDesObjets.getCompteInstance().getUtilisateur().getUsername(), result.getCompteBeneficiaire().getUtilisateur().getUsername());

	}
	@Test
	void EntityToDtoTransfer(){
		Transfer transfer = CreationDesObjets.getTransferInstance();
		TransferDto result = (TransferDto) transferMapper.entityToDto(transfer);

        assertEquals(BigDecimal.valueOf(5000),result.getMontant());

 		assertEquals("motif 2",result.getMotif());
		 assertEquals("account nbr2",result.getNrCompteEmetteur());
		assertEquals("account nbr1",result.getNrCompteBeneficiaire());
	}

	@Test
	void dtoToEntityTransferWillThrowExceptionWhenRecepteurIsNull() throws CompteNonExistantException {
		TransferDto transferDto = CreationDesObjets.getTransferDtoInstance();
		when(accountRepository.findByNrCompte(CreationDesObjets.getCompteInstance().getNrCompte())).thenReturn(null);

		assertThrows(CompteNonExistantException.class, () -> transferMapper.dtoToEntity(transferDto));


	}
	@Test
	void dtoToEntityTransferWillThrowExceptionWhenEmtteurIsNull() throws CompteNonExistantException {
		TransferDto transferDto = CreationDesObjets.getTransferDtoInstance();

		when(accountRepository.findByNrCompte(transferDto.getNrCompteBeneficiaire())).thenReturn(CreationDesObjets.getCompteInstance());
		when(accountRepository.findByNrCompte(CreationDesObjets.getCompteInstance1().getNrCompte())).thenReturn(null);
		assertThrows(CompteNonExistantException.class, () -> transferMapper.dtoToEntity(transferDto));


	}

}
