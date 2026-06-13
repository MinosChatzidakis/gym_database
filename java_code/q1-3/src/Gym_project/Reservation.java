package Gym_project;

import java.time.LocalDateTime;

public class Reservation {
	
	private int reservationCode;
	private LocalDateTime  dateAndTime;
	private int invoiceNeeded;
	private ReservationStatus reservationStatus;
	private int sessionCode;
	private int customerID;
	
	public Reservation(int reservationCode, LocalDateTime  dateAndTime, int invoiceNeeded, ReservationStatus reservationStatus, int sessionCode, int customerID) {
		this.reservationCode = reservationCode;
		this.dateAndTime = dateAndTime;
		this.invoiceNeeded = invoiceNeeded;
		this.reservationStatus = reservationStatus;
		this.sessionCode = sessionCode;
		this.customerID = customerID;
	}
	
	public Reservation(LocalDateTime  dateAndTime, int invoiceNeeded, ReservationStatus reservationStatus, int sessionCode, int customerID) {
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
	
	public int getInvoiceNeeded() {
		return invoiceNeeded;
	}
	
	public void setInvoiceNeeded(int invoiceNeeded) {
		if(invoiceNeeded!=1&&invoiceNeeded!=0) throw new IllegalArgumentException("Invalid input.");
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
	
	public void setSessionCode(int sessionCode) {
		this.sessionCode = sessionCode;
	}
	
	public int getCustomerID() {
		return customerID;
	}
	
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public boolean isPast() {
	    if (this.dateAndTime == null) {
	        return false; 
	    }

	    return this.dateAndTime.isBefore(LocalDateTime.now());
	}
}
