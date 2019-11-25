import java.util.Vector;

public class Login {
	private static Login instance = null;
	private LoginView login;
	private Database db;
	
	public Login(Database d) {
		db = d;
		setLogin(new LoginView());
	}
	
	public static Login getInstance(Database d)
	 {
	 	 if (instance == null)
	 		 	instance = new Login(d);
	 	return instance;
	 }
	 
	 public Boolean validate(RegisteredUser u)
	 {
		 return db.queryUser(u);
	 }

	public LoginView getLogin() {
		return login;
	}

	public void setLogin(LoginView login) {
		this.login = login;
	}
	 
	 
	
}
