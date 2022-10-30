package form;



import javax.servlet.http.HttpServletRequest;

/**
 * @author wannyould-amrouche
 *
 */
public class ConnectionForm {
	private String resultat;

	public void verif(HttpServletRequest request) {
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");

		if (pass.equals(login + "123")) {
			resultat = "estás conectado "+login;
			if (login.equals("Yuriamna")) {
				resultat = "estás conectado Wara";

			}

		} else {
			resultat = "identificador incorrecto";

		}

	}

	public String getResultat() {
		return resultat;

	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

}
