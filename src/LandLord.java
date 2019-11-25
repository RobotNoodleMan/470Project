import java.util.ArrayList;

public class LandLord extends RegisteredUser {
	private ArrayList<Property> postedProperties;
	private ArrayList<Payment> paymentHistory;
	private ChangeProperty changer;
	private Database db;
	
	public LandLord(Database da) {
		db = da;
		changer = new ChangeProperty(db);
	}
	public ArrayList<Property> getPostedProperties() {
		return postedProperties;
	}
	
	public void registerProperty(Property p) {
		if (changer.addProperty(p))
			postedProperties.add(p);
	}
	
	public void changePropertyState(Property toChange, int newState) {
		toChange.setPropertyState(newState);
		changer.setState(toChange, newState);
	}
	
	public void pay(double amount, Payment toPay) {
		toPay.makePayment(amount);
		paymentHistory.add(toPay);
	}
	
	public void setPostedProperties(ArrayList<Property> postedProperties) {
		this.postedProperties = postedProperties;
	}
	
	public ArrayList<Payment> getPaymentHistory() {
		return paymentHistory;
	}
	
	public void setPaymentHistory(ArrayList<Payment> paymentHistory) {
		this.paymentHistory = paymentHistory;
	}
	
	public static void setDBPaymentInfo(RegisteredUser userToChange, Database db)
	{
		String s;
		s = "UPDATE rentingdb.users SET payment_paid = CURRENT_TIMESTAMP WHERE users.user_name = '"+userToChange.getName()+"'";
		db.executeQuery(s);
		
		s = "UPDATE rentingdb.users SET payment_due = NOW()+INTERVAL 60 DAY WHERE users.user_name = '"+userToChange.getName()+"'";
		db.executeQuery(s);
	
	}
	
	
	


	
	
}
