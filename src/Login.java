import java.util.Vector;

public class Login {
	private static Login instance = null;
	private Database db;
	
	 public static Login getInstance()
	 {
	 	 if (instance == null)
	 		 	instance = new Login();
	 	return instance;
	 }
	 
	 public Boolean validate(RegisteredUser u)
	 {
		 return db.queryUser(u);
	 }
	 
	 
	
}
