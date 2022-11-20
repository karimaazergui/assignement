package ma.octo.assignement.service;

import ma.octo.assignement.domain.Account;
import ma.octo.assignement.exceptions.CompteNonExistantException;

import java.util.List;

public interface IAccountService {
    public List<Account> allAccounts();
    public Account getAccountByRib(String rib) throws CompteNonExistantException;
    Account getAccount(String nrCompte) throws CompteNonExistantException;
    Account addAccount(Account compte) throws Exception;
}
