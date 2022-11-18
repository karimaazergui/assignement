package ma.octo.assignement.web;

import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.repository.UtilisateurRepository;
import ma.octo.assignement.service.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/Users")
public class UserController {
    @Autowired
    IUtilisateurService iUtilisateurService;
    @GetMapping("listOfUsers")
    List<Utilisateur> loadAllUtilisateur() {
        return iUtilisateurService.loadAllUtilisateur();
    }
}
