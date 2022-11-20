package ma.octo.assignement.web;

import ma.octo.assignement.domain.Account;
import ma.octo.assignement.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping(value = "/accounts")
public class AccountController {
   @Autowired
   IAccountService iCompteService;
    @GetMapping("/")
    List<Account> allAccounts() {
   return iCompteService.allAccounts();
    }
}
