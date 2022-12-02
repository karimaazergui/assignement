package ma.octo.assignement;

import ma.octo.assignement.domain.Account;
import ma.octo.assignement.domain.Deposit;
import ma.octo.assignement.domain.Transfer;
import ma.octo.assignement.domain.User;
import ma.octo.assignement.dto.DepositDto;
import ma.octo.assignement.dto.TransferDto;

import java.math.BigDecimal;
import java.util.Date;

public class CreationDesObjets {

        private static final Date now = new Date();

        public static User getUserInstance(){
            User utilisateur1 = new User();
            utilisateur1.setUsername("user1");
            utilisateur1.setLastname("last1");
            utilisateur1.setFirstname("first1");
            utilisateur1.setGender("Male");
            utilisateur1.setActive(true);
            utilisateur1.setBirthdate(new Date());
            utilisateur1.setPassword("root");
            utilisateur1.setRole("ADMIN");
            return utilisateur1;
        }

        public static Account getCompteInstance(){
            Account compte = new Account();
            compte.setNrCompte("account nbr1");
            compte.setSolde(BigDecimal.valueOf(10000.0));
            compte.setRib("rib1");
            compte.setUtilisateur(getUserInstance());
            return compte;
        }
        public static Account getCompteInstance1(){
            Account compte = new Account();
            compte.setNrCompte("account nbr2");
            compte.setSolde(BigDecimal.valueOf(10000.0));
            compte.setRib("rib2");
            compte.setUtilisateur(getUserInstance());
            return compte;
        }
        public static DepositDto getDepositDtoInstance(){
            DepositDto depositDto = new DepositDto();
            depositDto.setNomPrenomEmetteur("karima azergui");
            depositDto.setMontant(BigDecimal.TEN);
            depositDto.setDate(now);
            depositDto.setNrCompteBeneficiaire("account nbr1");
            depositDto.setMotif("motif 1");
            return depositDto;
        }

        public static Deposit getDepositInstance(){
            Deposit deposit = new Deposit();
            deposit.setNomPrenomEmetteur("karima azergui");
            deposit.setMontant(BigDecimal.TEN);
            deposit.setMotif("motif 1");
            deposit.setDateExecution(now);
            deposit.setCompteBeneficiaire(getCompteInstance());
            return deposit;
        }
        public static Transfer getTransferInstance(){
            Transfer transfer = new Transfer();
            transfer.setMotif("motif 2");
            transfer.setMontant(BigDecimal.valueOf(5000));
            transfer.setDateExecution(now);
            transfer.setCompteBeneficiaire(getCompteInstance());
            transfer.setCompteEmetteur(getCompteInstance1());
            return transfer;
        }
        public static TransferDto getTransferDtoInstance(){
            TransferDto transferDto=new TransferDto();
            transferDto.setNrCompteEmetteur(getCompteInstance1().getNrCompte());
            transferDto.setMontant(BigDecimal.valueOf(2344));
            transferDto.setMotif("g");
            transferDto.setDate(new Date());
            transferDto.setNrCompteBeneficiaire(getCompteInstance().getNrCompte());
            return transferDto;
        }


    }


