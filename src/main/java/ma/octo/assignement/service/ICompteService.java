package ma.octo.assignement.service;

import ma.octo.assignement.domain.Compte;

import java.util.List;

public interface ICompteService {
    List<Compte> loadAllCompte();
}
