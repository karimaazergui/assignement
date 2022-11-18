package ma.octo.assignement.web;

import ma.octo.assignement.entities.Compte;
import ma.octo.assignement.service.ICompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController(value = "/Accounts")
public class AccountController {
   @Autowired
    ICompteService iCompteService;
    @GetMapping("/")
    List<Compte> loadAllCompte() {
   return iCompteService.loadAllCompte();
    }
}
