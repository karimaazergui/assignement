package ma.octo.assignement.service;

import ma.octo.assignement.domain.Compte;
import ma.octo.assignement.repository.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
@Service
public class CompteService implements ICompteService{
    @Autowired
    private CompteRepository compteRepository;
    @Override
    public List<Compte> loadAllCompte() {
        return CollectionUtils.isEmpty(compteRepository.findAll()) ? null :compteRepository.findAll();
    }
}
