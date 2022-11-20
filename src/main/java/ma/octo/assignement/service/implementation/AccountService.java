package ma.octo.assignement.service.implementation;

import ma.octo.assignement.entities.Account;
import ma.octo.assignement.exceptions.common.CompteNonExistantException;
import ma.octo.assignement.service.IAccountService;
import ma.octo.assignement.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Override
    public List<Account> allAccounts() {
        return CollectionUtils.isEmpty(accountRepository.findAll()) ? null :accountRepository.findAll();
    }
    @Override
    public Account getAccountByRib(String rib) throws CompteNonExistantException {
        Account compte = accountRepository.findByRib(rib);
        if(compte == null) {
            throw new CompteNonExistantException("Compte Non existant");
        }
        return compte;
    }

    @Override
    public Account getAccount(String nrCompte) throws CompteNonExistantException {
        Account compte = accountRepository.findByNrCompte(nrCompte);
        if( compte == null ) {
            throw new CompteNonExistantException("Compte Non existant");
        }
        return compte;
    }

    @Override
    public Account addAccount(Account compte) throws Exception {
        return accountRepository.save(compte);
    }
}
