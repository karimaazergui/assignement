package ma.octo.assignement.service.implementation;

import ma.octo.assignement.entities.User;
import ma.octo.assignement.repository.UserRepository;
import ma.octo.assignement.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository utilisateurRepository;
    @Override
    public List<User> allUsers() {
        return CollectionUtils.isEmpty(utilisateurRepository.findAll()) ? null :utilisateurRepository.findAll();
    }
}
