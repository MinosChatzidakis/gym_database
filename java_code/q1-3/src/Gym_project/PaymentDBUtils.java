package Gym_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PaymentDBUtils { 
	
	public static void addPayment(Payment p) {
	    
	    String sqlQuery = "INSERT INTO payment (amount, payment_Method, payment_Date, payment_Status, reservation_Reservation_Code, pts_Transactions_Trans_ID) VALUES ("
	            + p.getAmount() + ", '"
	            + p.getPaymentMethod() + "', '"
	            + p.getPaymentDate() + "', '"
	            + p.getPaymentStatus() + "', "
	            + p.getReservationCode() + ", "
	            + p.getTransID() + ")";
	            
	    try (Connection conn = SQLConnector.getConnection(); 
	         Statement stm = conn.createStatement()) {
	        
	        int rowsAffected = stm.executeUpdate(sqlQuery);
	        if (rowsAffected > 0) {
	            System.out.println("Payment was successfully added to the database.");
	        } else {
	            System.out.println("Something went wrong and the payment could not be added.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error in adding payment to db:");
	        e.printStackTrace();
	    }
	}
	
	//Κάνει το UPDATE στη βάση
	public static void updatePayment(Payment p) {
	    String sqlQuery = "UPDATE payment SET "
	            + "amount = " + p.getAmount() + ", "
	            + "payment_Method = '" + p.getPaymentMethod() + "', "
	            + "payment_Status = '" + p.getPaymentStatus() + "' "
	            + "WHERE payment_ID = " + p.getPaymentID();
	            
	    try (Connection conn = SQLConnector.getConnection(); 
	         Statement stm = conn.createStatement()) {
	        
	        int rowsAffected = stm.executeUpdate(sqlQuery);
	        if (rowsAffected > 0) {
	            System.out.println("Payment data updated successfully in the database.");
	        } else {
	            System.out.println("No changes were made to the Payment.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error updating Payment data in db:");
	        e.printStackTrace();
	    }
	}
	
	//Φέρνει την πληρωμή από το ID
	public static Payment getPaymentByID(int paymentId) {
	    String sql = "SELECT * FROM payment WHERE payment_ID = " + paymentId + " LIMIT 1";
	    try (Connection conn = SQLConnector.getConnection(); 
	         Statement stm = conn.createStatement();
	         ResultSet res = stm.executeQuery(sql)) {		

	        if (res.next()) { 
	            return new Payment(
	                res.getInt("payment_ID"),
	                res.getInt("amount"),
	                res.getString("payment_Method"),
	                res.getString("payment_Date"),
	                res.getInt("reservation_Reservation_Code"),
	                res.getInt("pts_Transactions_Trans_ID"),
	                res.getString("payment_Status")
	            );
	        }				    
	    } catch (SQLException e) {
	        System.out.println("Error fetching payment by ID:");
	        e.printStackTrace();
	    }
	    return null;
	}

	public static ArrayList<PendingPayment> getPendingPayments() {
		String sql= "SELECT p.payment_ID as pid, p.payment_Status as pstatus, p.payment_Method as pmethod, p.amount as pamount, "
				+ "r.date_And_Time as rdate, "
				+ "s.date_And_Time as sdate, "
				+ "c.name as cname "
				+ "FROM payment p JOIN reservation r ON p.reservation_Reservation_Code = r.reservation_code "
				+ "JOIN customer c ON r.customer_Customer_ID = c.customer_ID "
				+ "JOIN session s ON s.session_Code = r.session_Session_Code "
				+ "WHERE p.payment_status = 'PENDING' AND r.reservation_status = 'PENDING';";
				
		try {
			Connection conn = SQLConnector.getConnection(); 
			Statement stm= conn.createStatement();
			ResultSet res= stm.executeQuery(sql);
			ArrayList<PendingPayment> pendingPayments= new ArrayList<>();
			
			// ΠΡΟΣΘΗΚΗ: Φτιάχνουμε τον Formatter που θα διαβάζει τα String της βάσης
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			
			while(res.next()) {
			    // 1. Παίρνουμε τις ημερομηνίες ως απλά Strings
			    String rDateStr = res.getString("rdate");
			    String sDateStr = res.getString("sdate");
			    
			    // 2. Τις μετατρέπουμε σε αντικείμενα LocalDateTime
			    LocalDateTime resDate = LocalDateTime.parse(rDateStr, formatter);
			    LocalDateTime sesDate = LocalDateTime.parse(sDateStr, formatter);
			    
				PendingPayment currentPendingPayment= new PendingPayment(
							res.getInt("pid"), 
							PaymentMethods.valueOf(res.getString("pmethod")),
							PaymentStatus.valueOf(res.getString("pstatus")),
							res.getString("cname"),
							resDate, // Περνάμε το έτοιμο LocalDateTime της Κράτησης
							sesDate, // Περνάμε το έτοιμο LocalDateTime του Session
							res.getFloat("pamount")
						);
						
				pendingPayments.add(currentPendingPayment);
			}
			return pendingPayments; 
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null; 
	}
}