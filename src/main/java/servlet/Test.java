package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import Beans.Auteur;
import form.ConnectionForm;

/**
 * Servlet implementation class Test
 */

@WebServlet
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }



    public static final int TAILLE_TAMPON = 10240;
    public static final String CHEMIN_FICHIERS = "/Users/wannyould-amrouche/Documents/applijeefichichierupload/"; // A changer
    
	String[] titres = { "spider", "rend les falafels", "elle est ou la timballe" };
	Auteur auteur = new Auteur();
	String message = "Yuri eres mi bibi";
	String todaysdate = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setAttribute("titres", titres);

		
		auteur.setFirstName("Wanny");
		auteur.setName("OA");
		auteur.setIsActive(true);
		request.setAttribute("auteur", auteur);

		
		StringBuilder sb = new StringBuilder();
		message = sb.append(message).reverse().toString();
		sb.reverse().append(message).toString();
		request.setAttribute("variable", message);

		
		
		HttpSession session = request.getSession();
		request.setAttribute("titres", titres);

		session.setAttribute("variable", message);
		session.setAttribute("auteur", auteur);
	
		


		request.setAttribute("time", todaysdate);
		session.setAttribute("time", todaysdate);
		
getServletContext().getRequestDispatcher("/WEB-INF/hola.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		
		  ConnectionForm form = new ConnectionForm(); form.verif(request);
		  request.setAttribute("form", form);
		 
		
		
		  // On récupère le champ description comme d'habitude
        String description = request.getParameter("description");
        request.setAttribute("description", description );

        // On récupère le champ du fichier
        Part part = request.getPart("fichier");
            
        // On vérifie qu'on a bien reçu un fichier
        String nomFichier = getNomFichier(part);

        // Si on a bien un fichier
        if (nomFichier != null && !nomFichier.isEmpty()) {
            String nomChamp = part.getName();
            // Corrige un bug du fonctionnement d'Internet Explorer
             nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
                    .substring(nomFichier.lastIndexOf('\\') + 1);

            // On écrit définitivement le fichier sur le disque
            ecrireFichier(part, nomFichier, CHEMIN_FICHIERS);

            request.setAttribute(nomChamp, nomFichier);
		
        }
		
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/hola.jsp").forward(request, response);
		
		

		/**
		 * code pour form contuens mauvaise paratique car le traitement de donées ne
		 * doit pas s'effectuer directement dans une sevrlet
		 */
		// request.setAttribute("nom", nom);
		// System.out.println("Todlddddllm: "+nom );
		// System.out.println(response) ;

		// TODO Auto-generated method stub
		// doGet(request, response);
	}
	
	

    private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);

            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                sortie.close();
            } catch (IOException ignore) {
            }
            try {
                entree.close();
            } catch (IOException ignore) {
            }
        }
    }
    
    private static String getNomFichier( Part part ) {
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    }   

}
