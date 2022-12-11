package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Beans.Auteur;
import form.ConnectionForm;

/**
 * Servlet implementation class Test
 */

@WebServlet
public class Test2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Test2() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static final int TAILLE_TAMPON = 10240;
	public static final String CHEMIN_FICHIERS = "/Users/wannyould-amrouche/Documents/applijeefichichierupload/"; // A
																													// changer

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ConnectionForm form = new ConnectionForm();
		form.verif(request);
		request.setAttribute("form", form);
		Cookie cookie=new Cookie("user",form.getLogin());
		cookie.setMaxAge(60*60*24);
		response.addCookie(cookie);
		
		
		Cookie [] cookies = request.getCookies();
		if(cookies!=null) {
			for(Cookie cookieN: cookies) {
				if(cookie.getName().equals("user")) {
					request.setAttribute("user", cookieN.getValue());
					
				}
				
			}
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/Bdd.jsp").forward(request, response);

	}

}
