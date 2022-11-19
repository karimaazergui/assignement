package ma.octo.assignement.controller;

import ma.octo.assignement.entities.User;
import ma.octo.assignement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/users")
public class UserController {
    @Autowired
    IUserService iUtilisateurService;
    @GetMapping("/")
    List<User> allUsers() {
        return iUtilisateurService.allUsers();
    }
}
