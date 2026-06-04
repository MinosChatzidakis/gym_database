package gym_project;

public class Reservation {
	private String reservation_code;
	private String date_and_time;
	private String invoice_Needed;
	private String reservation_status;
	private int session_code;
	private int customer_ID;
	
	public Reservation(String reservation_code, String date_and_time, String invoice_Needed, String reservation_status, int session_code, int customer_ID) {
		this.reservation_code = reservation_code;
		this.date_and_time = date_and_time;
		this.invoice_Needed = invoice_Needed;
		this.reservation_status = reservation_status;
		this.session_code = session_code;
		this.customer_ID = customer_ID;
	}
	
	public String getReservation_code() {
		return reservation_code;
	}
	
	public void setReservation_code(String reservation_code) {
		this.reservation_code = reservation_code;
	}
	
	public String getDate_and_time() {
		return date_and_time;
	}
	
	public void setDate_and_time(String date_and_time) {
		this.date_and_time = date_and_time;
	}
	
	public String getInvoice_Needed() {
		return invoice_Needed;
	}
	
	public void setInvoice_Needed(String invoice_Needed) {
		this.invoice_Needed = invoice_Needed;
	}
	
	public String getReservation_status() {
		return reservation_status;
	}
	
	public void setReservation_status(String reservation_status) {
		this.reservation_status = reservation_status;
	}
	
	public int getSession_code() {
		return session_code;
	}
	
	public void setSession_code(int session_code) {
		this.session_code = session_code;
	}
	
	public int getcustomer_ID() {
		return customer_ID;
	}
	
	public void setCustomer_ID(int customer_ID) {
		this.customer_ID = customer_ID;
	}

}
