package Gym_project;

public class Payment {

	
	private int paymentID;
	private int amount;
	private String paymentMethod;
	private String paymentDate;
	private String reservationCode;
	private PaymentStatus paymentStatus;
	private int transID;
	
	
	
	public Payment(int paymentID , int amount, String paymentMethod, String paymentDate, String reservationCode, int transID) {
		
		this.paymentID = paymentID;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.paymentDate = paymentDate;
		this.reservationCode = reservationCode;
		this.transID = transID;
		
	}
	
	
	public int getPaymentID() {
		return paymentID;
	}
	
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public String getReservationCode() {
		return reservationCode;
	}
	
	public int getTransID() {
		return transID;
	}
	
	public void setTransID(int transID) {
		this.transID = transID;
	}
	
	public String getPaymentDate() {
		return paymentDate;
	}
	
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
}


