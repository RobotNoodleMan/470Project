import java.sql.Date;

public class Payment {
	protected int paymentState;
	protected double amount;
	protected Date postedDate;
	protected Date paidDate;
	
	public Payment(double a) {
		// TODO Auto-generated constructor stub
		paymentState = 0;
		amount = a;
		postedDate = new Date (System.currentTimeMillis());
	}
	
	public void makePayment(double amountPaid) {
		paymentState = 1;
		amount -= amountPaid;
		paidDate = new Date (System.currentTimeMillis());
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
	
	public Date getPostedDate() {
		return postedDate;
	}
	
	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}
	
	public Date getPaidDate() {
		return paidDate;
	}
	
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	
	
}
