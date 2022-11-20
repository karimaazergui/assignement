package ma.octo.assignement;

import ma.octo.assignement.entities.Account;

import ma.octo.assignement.entities.User;
import ma.octo.assignement.entities.Transfer;
import ma.octo.assignement.mapper.ITransferMapper;

import ma.octo.assignement.service.IAccountService;
import ma.octo.assignement.service.ITransferService;
import ma.octo.assignement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.Date;

@SpringBootApplication
public class NiceBankApplication implements CommandLineRunner {
	@Autowired
	private IAccountService compteService;
	@Autowired
	private IUserService utilisateurService;
	@Autowired
	private ITransferService transferService;
	@Autowired
	ITransferMapper iTransferMapper;
	@Autowired
	private PasswordEncoder encoder;
	public static void main(String[] args) {
		SpringApplication.run(NiceBankApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		User utilisateur1 = new User();
		utilisateur1.setUsername("user1");
		utilisateur1.setLastname("last1");
		utilisateur1.setFirstname("first1");
		utilisateur1.setGender("Male");
		utilisateur1.setPassWord(encoder.encode("rootroot"));
		utilisateurService.addUtilisateur(utilisateur1);


		User utilisateur2 = new User();
		utilisateur2.setUsername("user2");
		utilisateur2.setLastname("last2");
		utilisateur2.setFirstname("first2");
		utilisateur2.setPassWord(encoder.encode("rootroot"));
		utilisateur2.setGender("Female");
		utilisateurService.addUtilisateur(utilisateur2);

		Account compte1 = new Account();
		compte1.setNrCompte("010000A000001000");
		compte1.setRib("RIB1");
		compte1.setSolde(BigDecimal.valueOf(200000L));
		compte1.setUtilisateur(utilisateur1);
		compteService.addAccount(compte1);

		Account compte2 = new Account();
		compte2.setNrCompte("010000B025001000");
		compte2.setRib("RIB2");
		compte2.setSolde(BigDecimal.valueOf(140000L));
		compte2.setUtilisateur(utilisateur2);
		compteService.addAccount(compte2);

		Transfer v = new Transfer();
		v.setMontantTransfer(BigDecimal.TEN);
		v.setCompteBeneficiaire(compte2);
		v.setCompteEmetteur(compte1);
		v.setDateExecution(new Date());
		v.setMotifTransfer("Assignment 2021");

		transferService.createTransaction(iTransferMapper.entityToDto(v));
	}
}
