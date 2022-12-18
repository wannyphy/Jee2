package DAO;

import java.util.List;

import Beans.Utilisateur;

public interface UtilisateurDao {

	void ajouter(Utilisateur utilisateur);

	List<Utilisateur> lister();

}
