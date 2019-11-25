import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Payment {
	protected int paymentState;
	protected double amount;
	protected Timestamp postedTimestamp;
	protected Timestamp paidTimestamp;
	protected Timestamp dueTimestamp;
	
	public Payment(double a) {
		// TODO Auto-generated constructor stub
		paymentState = 0;
		amount = a;
		postedTimestamp = new Timestamp (System.currentTimeMillis());
	}
	
	public void makePayment(double amountPaid) {
		paymentState = 1;
		amount -= amountPaid;
		paidTimestamp = new Timestamp (System.currentTimeMillis());
	}

	public int getPaymentState() {
		return paymentState;
	}
	
	public void setPaymentState(int paymentState) {
		this.paymentState = paymentState;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public Timestamp getPostedTimestamp() {
		return postedTimestamp;
	}
	
	public void setPostedTimestamp(Timestamp postedTimestamp) {
		this.postedTimestamp = postedTimestamp;
	}
	
	public Timestamp getPaidTimestamp() {
		return paidTimestamp;
	}
	
	public void setPaidTimestamp(Timestamp paidTimestamp) {
		this.paidTimestamp = paidTimestamp;
	}
	
	public Timestamp getDueTimestamp() {
		return dueTimestamp;
	}
	
	public void setDueTimestamp(Timestamp dueTimestamp) {
		this.dueTimestamp = dueTimestamp;
	}
	
	
	public String timeToTimestampString(Timestamp timestamp)
	{
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(timestamp);
		return timeStamp;
	}
	
	public static void main(String[] args) {
		
		Payment p = new Payment(1);
		System.out.println(p.timeToTimestampString(p.postedTimestamp));
		
	}
	

}
	
	

