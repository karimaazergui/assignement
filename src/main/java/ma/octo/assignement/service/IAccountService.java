package ma.octo.assignement.service;

import ma.octo.assignement.entities.Account;
import ma.octo.assignement.exceptions.common.CompteNonExistantException;

import java.util.List;

public interface IAccountService {
    public List<Account> allAccounts();
    public Account getCompteByRib(String rib) throws CompteNonExistantException;
}
