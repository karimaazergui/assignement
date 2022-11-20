package ma.octo.assignement.service;

import ma.octo.assignement.domain.User;

import java.util.List;

public interface IUserService {
    List<User> allUsers();
    void addUser(User utilisateur);

}
