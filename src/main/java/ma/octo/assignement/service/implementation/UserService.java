package ma.octo.assignement.service.implementation;

import ma.octo.assignement.domain.User;
import ma.octo.assignement.repository.UserRepository;
import ma.octo.assignement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    @Override
    public void addUser(User utilisateur) {
         userRepository.save(utilisateur);
    }
}
