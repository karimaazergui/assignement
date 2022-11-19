package ma.octo.assignement.controller;

import ma.octo.assignement.entities.Account;
import ma.octo.assignement.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController(value = "/accounts")
public class AccountController {
   @Autowired
   IAccountService iCompteService;
    @GetMapping("/")
    List<Account> allAccounts() {
   return iCompteService.allAccounts();
    }
}
