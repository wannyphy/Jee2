package bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Beans.Utilisateur;

public class Noms {

	private Connection connexion;

	public List<Utilisateur> recupereUtilisateurs() {

		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		Statement statement = null;
		ResultSet resultat = null;

		loadDatabase();

		try {
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT nom,prenom FROM noms;");

			while (resultat.next()) {
				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");

				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setNom(nom);
				utilisateur.setPrenom(prenom);

				utilisateurs.add(utilisateur);
			}
		} catch (SQLException e) {
		} finally {
			try {
				if (resultat != null)
					resultat.close();
				if (statement != null)
					statement.close();
				if (connexion != null)
					connexion.close();
			} catch (SQLException ignore) {

			}
		}
		return utilisateurs;
	}

	private void loadDatabase() {
		// Chargement du driver
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		}

		try {
			connexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaee", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void ajouterUtilisateur(Utilisateur utilisateur) {
		loadDatabase();

		try {
			PreparedStatement preparedstaement = connexion
					.prepareStatement("INSERT INTO noms(nom, prenom) VALUES(?, ?);");
			preparedstaement.setString(1, utilisateur.getNom());
			preparedstaement.setString(2, utilisateur.getPrenom());

			preparedstaement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
