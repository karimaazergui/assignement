package ma.octo.assignement.web;

import ma.octo.assignement.entities.Utilisateur;
import ma.octo.assignement.service.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/Users")
public class UserController {
    @Autowired
    IUtilisateurService iUtilisateurService;
    @GetMapping("/")
    List<Utilisateur> loadAllUtilisateur() {
        return iUtilisateurService.loadAllUtilisateur();
    }
}
