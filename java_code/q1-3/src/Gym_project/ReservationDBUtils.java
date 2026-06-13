package Gym_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservationDBUtils {
	
	public static void addReservation(Reservation r) {
	    
	    String sqlQuery = "INSERT INTO reservation (date_And_Time, invoice_Needed, reservation_Status, session_Session_Code, customer_Customer_ID) VALUES ('"
	            + r.getDateAndTime() + "', "
	            + (r.getInvoiceNeeded() ? 1 : 0) + ", '"
	            + r.getReservationStatus() + "', "
	            + r.getSessionCode() + ", "
	            + r.getCustomerID() + ")";
	    
	    try (Connection conn = SQLConnector.getConnection(); 
	         Statement stm = conn.createStatement()) {
	        
	        int rowsAffected = stm.executeUpdate(sqlQuery);
	        if (rowsAffected > 0) {
	            System.out.println("Reservation was successfully added to the database.");
	        } else {
	            System.out.println("Something went wrong and the reservation could not be added.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error in adding reservation to db:");
	        e.printStackTrace();
	    }
	}
	
	public static void updateReservation(Reservation r) {
	    
	    String sqlQuery = "UPDATE reservation SET "
	            + "date_And_Time = '" + r.getDateAndTime() + "', "
	            + "invoice_Needed = " + (r.getInvoiceNeeded() ? 1 : 0) + ", "
	            + "reservation_Status = '" + r.getReservationStatus() + "', "
	            + "session_Session_Code = " + r.getSessionCode() + ", "
	            + "customer_Customer_ID = " + r.getCustomerID()
	            + " WHERE reservation_Code = " + r.getReservationCode();
	            
	    try (Connection conn = SQLConnector.getConnection(); 
	         Statement stm = conn.createStatement()) {
	        
	        int rowsAffected = stm.executeUpdate(sqlQuery);
	        if (rowsAffected > 0) {
	            System.out.println("Reservation data updated successfully in the database.");
	        } else {
	            System.out.println("No changes were made (data might be identical).");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error updating Reservation data in db:");
	        e.printStackTrace();
	    }
	}
	
	public static Reservation getReservationByID(int id) {
	    String sql = "SELECT * FROM reservation WHERE reservation_Code = " + id + " LIMIT 1";
	    try (Connection conn = SQLConnector.getConnection(); 
	         Statement stm = conn.createStatement();
	         ResultSet res = stm.executeQuery(sql)) {		

	        if (res.next()) { 
	            return new Reservation(
	                res.getInt("reservation_Code"),
	                res.getString("date_And_Time"),
	                res.getBoolean("invoice_Needed"),
	                res.getString("reservation_Status"),
	                res.getInt("session_Session_Code"),
	                res.getInt("customer_Customer_ID")
	            );
	        }				    
	    } catch (SQLException e) {
	        System.out.println("Error fetching reservation by ID:");
	        e.printStackTrace();
	    }
	    return null; // Επιστρέφει null αν δεν βρεθεί η κράτηση
	}
	
	public ResultSet getActiveReservations() {
		String sqlQuery= "SELECT * FROM reservation WHERE reservation_status = 'PENDING' OR reservation_status = 'COMPLETE' ORDER BY reservation_status;";
		try {
			Connection conn= SQLConnector.getConnection();
			Statement stm= conn.createStatement();
			ResultSet res= stm.executeQuery(sqlQuery);
			if(res.next()) {
				return res;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public ResultSet getUnpaidReservations() {
		// payment_status= pending && session_status= pending
		String sql= "SELECT * FROM payment p JOIN reservation r ON p.Reservation_code = r.reservation_code WHERE p.payment_status = 'PENDING' AND r.reservation_status = 'PENDING'";
		try {
			Connection conn = SQLConnector.getConnection(); //establish connection via the class we created
			Statement stm= conn.createStatement();
			ResultSet res= stm.executeQuery(sql);
			if(res.next()) {
				return res;
			}else {
				System.out.println("Something went wrong and the reservation could not be added to db.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
