package ma.octo.assignement.service;

import ma.octo.assignement.entities.Utilisateur;

import java.util.List;

public interface IUtilisateurService {
    List<Utilisateur> loadAllUtilisateur();
}
