package Gym_project;

import java.time.LocalDateTime ;

public class Payment {

	
	private int paymentID;
	private float amount;
	private PaymentMethods paymentMethod;
	private LocalDateTime  paymentDate;
	private int reservationCode;
	private PaymentStatus paymentStatus;
	private int transID;
	
	
	
	public Payment(/*int paymentID ,*/ float amount, PaymentMethods paymentMethod, LocalDateTime paymentDate, int reservationCode,/* int transID ,*/ PaymentStatus paymentStatus) {
		//this.paymentID = paymentID;
		this.amount = amount;
		this.paymentMethod = paymentMethod;
		this.paymentDate = paymentDate;
		this.reservationCode = reservationCode;
		//this.transID = transID; -- this concerns question 4 -- possible need to rename to pts_Transactions_Trans_ID 
		//this.paymentStatus = PaymentStatus.PENDING; -- consider this approach
		this.paymentStatus = paymentStatus;
	}
	
	
	public int getPaymentID() {
		return paymentID;
	}
	
	public void setPaymentID(int paymentID) {
		this.paymentID = paymentID;
	}
	
	public float getAmount() {
		return amount;
	}
	
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public PaymentMethods getPaymentMethod() {
		return paymentMethod;
	}
	
	public void setPaymentMethod(PaymentMethods paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	public int getReservationCode() {
		return reservationCode;
	}
	
	public int getTransID() {
		return transID;
	}
	
	public void setTransID(int transID) {
		this.transID = transID;
	}
	
	public LocalDateTime  getPaymentDate() {
		return paymentDate;
	}
	
	public void setPaymentDate(LocalDateTime  paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
}


