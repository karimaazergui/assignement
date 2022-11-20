package ma.octo.assignement.service;

import ma.octo.assignement.entities.User;

import java.util.List;

public interface IUserService {
    List<User> allUsers();
    void addUtilisateur(User utilisateur);

}
