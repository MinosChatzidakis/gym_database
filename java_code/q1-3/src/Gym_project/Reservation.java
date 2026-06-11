package Gym_project;

public class Reservation {
	
	private String reservationCode;
	private String dateAndTime;
	private boolean invoiceNeeded;
	private String reservationStatus;
	private int sessionCode;
	private int customerID;
	
	public Reservation(String reservationCode, String dateAndTime, boolean invoiceNeeded, String reservationStatus, int sessionCode, int customerID) {
		this.reservationCode = reservationCode;
		this.dateAndTime = dateAndTime;
		this.invoiceNeeded = invoiceNeeded;
		this.reservationStatus = reservationStatus;
		this.sessionCode = sessionCode;
		this.customerID = customerID;
	}
	
	public Reservation(){
		
	}
	
	public String getReservationCode() { 
		return reservationCode;
	}

	public void setReservationCode(String reservationCode) {
		this.reservationCode = reservationCode;
	}
	
	public String getDateAndTime() {
		return dateAndTime;
	}
	
	public void setDateAndTime(String dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
	
	public boolean getInvoiceNeeded() {
		return invoiceNeeded;
	}
	
	public void setInvoiceNeeded(boolean invoiceNeeded) {
		this.invoiceNeeded = invoiceNeeded;
	}
	
	public String getReservationStatus() {
		return reservationStatus;
	}
	
	public void setReservationStatus(String reservationStatus) {
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
