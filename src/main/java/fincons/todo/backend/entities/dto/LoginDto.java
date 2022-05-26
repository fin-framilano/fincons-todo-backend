package fincons.todo.backend.entities.dto;

/**
 * Oggetto temporaneo per il controllo delle credenziali in seguito a un login
 * @author francesco
 *
 */

public class LoginDto {

	private String mail;
	private String password;

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDto [mail=" + mail + ", password=" + password + "]";
	}
	
	

}
