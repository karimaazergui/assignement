package ma.octo.assignement.service;

import ma.octo.assignement.domain.Utilisateur;
import ma.octo.assignement.repository.CompteRepository;
import ma.octo.assignement.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
@Service
public class UtilisateurService implements IUtilisateurService{
    @Autowired
    UtilisateurRepository utilisateurRepository;
    @Override
    public List<Utilisateur> loadAllUtilisateur() {
        return CollectionUtils.isEmpty(utilisateurRepository.findAll()) ? null :utilisateurRepository.findAll();
    }
}
