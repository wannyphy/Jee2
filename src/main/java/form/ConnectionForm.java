package form;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author wannyould-amrouche
 *
 */
public class ConnectionForm {
	private String resultat;
	private String login;
	private String pass;

	public void verif(HttpServletRequest request) {

		login = request.getParameter("login");
		pass = request.getParameter("pass");

		if (pass.equals(login + "123")) {
			resultat = "estás conectado " + login;

			if (login.equals("Yuriamna")) {
				resultat = "estás conectado Wara";

			}

		} else {
			resultat = "identificador incorrecto";

		}

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getResultat() {
		return resultat;

	}

}
