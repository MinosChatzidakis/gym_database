package Gym_project;

import java.time.LocalDateTime;

public class Reservation {
	
	private int reservationCode;
	private LocalDateTime  dateAndTime;
	private boolean invoiceNeeded;
	private ReservationStatus reservationStatus;
	private int sessionCode;
	private int customerID;
	
	public Reservation(int reservationCode, LocalDateTime  dateAndTime, boolean invoiceNeeded, ReservationStatus reservationStatus, int sessionCode, int customerID) {
		this.reservationCode = reservationCode;
		this.dateAndTime = dateAndTime;
		this.invoiceNeeded = invoiceNeeded;
		this.reservationStatus = reservationStatus;
		this.sessionCode = sessionCode;
		this.customerID = customerID;
	}
	
	
	public int getReservationCode() { 
		return reservationCode;
	}

	public void setReservationCode(int reservationCode) {
		this.reservationCode = reservationCode;
	}
	
	public LocalDateTime getDateAndTime() {
		return dateAndTime;
	}
	
	public void setDateAndTime(LocalDateTime dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	
	public boolean getInvoiceNeeded() {
		return invoiceNeeded;
	}
	
	public void setInvoiceNeeded(boolean invoiceNeeded) {
		this.invoiceNeeded = invoiceNeeded;
	}
	
	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}
	
	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}
	
	public int getSessionCode() {
		return sessionCode;
	}
	
	public int getcustomerID() {
		return customerID;
	}
	
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

}
