package Gym_project;

public class Payment {
	
	private int paymentID;
	private int amount;
	private String paymentMethod;
	private String paymentDate;
	private int reservationCode; 
	private String paymentStatus; 
	private int transID;
	
	
	public Payment(int paymentID, int amount, String paymentMethod, String paymentDate, int reservationCode, int transID, String paymentStatus) {
		this.paymentID = paymentID;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.paymentDate = paymentDate;
		this.reservationCode = reservationCode;
		this.transID = transID;
		this.paymentStatus = paymentStatus;
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
	
	public String getPaymentDate() {
		return paymentDate;
	}
	
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	// ΠΡΟΣΘΗΚΗ: Getter & Setter για το Reservation Code ως int
	public int getReservationCode() {
		return reservationCode;
	}
	
	public void setReservationCode(int reservationCode) {
		this.reservationCode = reservationCode;
	}
	
	public int getTransID() {
		return transID;
	}
	
	public void setTransID(int transID) {
		this.transID = transID;
	}
	
	// ΠΡΟΣΘΗΚΗ: Getter & Setter για το Payment Status ως String
	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
}