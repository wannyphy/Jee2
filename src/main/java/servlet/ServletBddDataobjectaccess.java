package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.Utilisateur;
import DAO.UtilisateurDao;
import DAOImpl.DaoFactory;

public class ServletBddDataobjectaccess extends HttpServlet {
	
 static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;

	public void init()throws ServletException{
		DaoFactory daoFactory=DaoFactory.getInstance();
		utilisateurDao= daoFactory.getUtilisateurDao();
	}
		protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
			
			request.setAttribute("utilisateur", utilisateurDao.lister());
			getServletContext().getRequestDispatcher("/WEB-INF/Bdd.jsp").forward(request, response);
		}
		
	    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	        Utilisateur utilisateur = new Utilisateur();
	        utilisateur.setNom(request.getParameter("nom"));
	        utilisateur.setPrenom(request.getParameter("prenom"));
	        
	        utilisateurDao.ajouter(utilisateur);
	        
	        request.setAttribute("utilisateurs", utilisateurDao.lister());
	        
	        this.getServletContext().getRequestDispatcher("/WEB-INF/Bdd.jsp").forward(request, response);
	    }
		
		
	
}
