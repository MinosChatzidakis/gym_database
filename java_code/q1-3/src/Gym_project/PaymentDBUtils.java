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
import java.util.ArrayList;

public abstract class PaymentDBUtils {
	// get payments that are still pending
	public static ArrayList<PendingPayment> getPendingPayments() {
		// payment_status= pending && session_status= pending
		String sql= "SELECT p.payment_ID as pid, p.payment_Status as pstatus, p.payment_Method as pmethod, p.amount as pamount, "
				+ "r.date_And_Time as rdate, "
				+ "s.date_And_Time as sdate, "
				+ "c.name as cname "
				+ "FROM payment p JOIN reservation r ON p.reservation_Reservation_Code = r.reservation_code "
				+ "JOIN customer c ON r.customer_Customer_ID = c.customer_ID "
				+ "JOIN session s ON s.session_Code = r.session_Session_Code "
				+ "WHERE p.payment_status = 'PENDING' AND r.reservation_status = 'PENDING'; ";
		try {
			Connection conn = SQLConnector.getConnection(); //establish connection via the class we created
			Statement stm= conn.createStatement();
			ResultSet res= stm.executeQuery(sql);
			ArrayList<PendingPayment> pendingPayments= new ArrayList<>();
			while(res.next()) {
				/*Payment currentPayment= new Payment(
						res.getFloat("amount"),
						PaymentMethods.valueOf(res.getString("paymentMethod")), //change this to the value of the local enumeration we have in place
						res.getObject("date_And_Time", LocalDateTime.class),
						res.getInt("reservation_Reservation_Code"),
						PaymentStatus.valueOf(res.getString("payment_Status")) //change this to the value of the local enumeration we have in place
						//date_And_Time gets the reservation date
						//customer_Customer_ID gets the customer ID
						);*/

				PendingPayment currentPendingPayment= new PendingPayment(
							res.getInt("pid"),
							PaymentMethods.valueOf(res.getString("pmethod").toUpperCase()),
							PaymentStatus.valueOf(res.getString("pstatus").toUpperCase()),
							res.getString("cname"),
							res.getObject("rdate", LocalDateTime.class), // date of reservation
							res.getObject("sdate", LocalDateTime.class), //date of session
							res.getFloat("pamount")
						);
						
				pendingPayments.add(currentPendingPayment);
			}
			return pendingPayments; // return found pending payments
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null; // no pending payments were found
	}
	
	// add payment to database
	public static int addPayment(Payment p) throws SQLException {
	    String dateValueForSQL;//handle the possibility of a null date
	    if (p.getPaymentDate() == null) {
	        dateValueForSQL = "NULL"; // SQL keyword for no data
	    } else {
	        dateValueForSQL = "'" + java.sql.Timestamp.valueOf(p.getPaymentDate()) + "'"; //actual date
	    }
	    
	    String sqlQuery = "INSERT INTO payment (amount, payment_Method, payment_Date, payment_Status, reservation_Reservation_Code, pts_Transactions_Trans_ID) VALUES (" 
	            + p.getAmount() + ", '" 
	            + p.getPaymentMethod().name() + "', " 
	            + dateValueForSQL + ", '" 
	            + p.getPaymentStatus().name() + "', " 
	            + p.getReservationCode() + ", " 
	            + p.getTransID() + ")";

	    try (Connection conn = SQLConnector.getConnection();
	         Statement stmt = conn.createStatement()) {
	        
	        stmt.executeUpdate(sqlQuery, Statement.RETURN_GENERATED_KEYS);
	        
	        try (ResultSet rs = stmt.getGeneratedKeys()) {
	            if (rs.next()) {
	                return rs.getInt(1);
	            } else {
	                throw new SQLException("Payment inserted, but no ID was generated."); // no id recorded = throw error
	            }
	        } 
	    } 
	}
	
	public static boolean confirmPaymentChangeInDB(int paymentId) {
		String updatePaymentSql = "UPDATE payment set payment_Status = 'COMPLETE' WHERE payment_ID = " + paymentId + ";";
		String updateReservationSql = "UPDATE reservation "
									+ "SET reservation_Status = 'COMPLETE' "
									+ "WHERE reservation_Code = (SELECT reservation_Reservation_Code FROM payment WHERE payment_ID = " + paymentId + ");";
		
		try (Connection conn = SQLConnector.getConnection();
				Statement stm = conn.createStatement()){
			
			stm.executeUpdate(updatePaymentSql);
			stm.executeUpdate(updateReservationSql);
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static void displayAllPaymentsInDB() {
		String sql = "SELECT p.payment_ID, p.amount, p.payment_Method, p.payment_Status, c.name as cname "
	               + "FROM payment p "
	               + "JOIN reservation r ON p.reservation_Reservation_Code = r.reservation_Code "
	               + "JOIN customer c ON r.customer_Customer_ID = c.customer_ID "
	               + "ORDER BY p.payment_ID DESC;";
		
		try(Connection conn = SQLConnector.getConnection();
				Statement stm = conn.createStatement();
				ResultSet res =stm.executeQuery(sql)){
			
			
			System.out.printf("%-10s | %-10s | %-15s | %-12s | %-25s\n", "Pay ID", "Amount", "Method", "Status", "Customer Name");
			
			boolean hasRecords = false;
			while (res.next()) {
				hasRecords = true;
				System.out.printf("%-10d | %-10s | %-15s | %-12s | %-25s\n", 
                        res.getInt("payment_ID"),
                        res.getFloat("amount") + " €",
                        res.getString("payment_Method"),
                        res.getString("payment_Status").toUpperCase(),
                        res.getString("cname"));
			}
			
			if(!hasRecords) {
				System.out.println("No Payments found in the database. ");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public static boolean updatePaymentStatusd(int paymentId, PaymentStatus newStatus) {
		String sql = "UPDATE payment SET payment_Status = '" + newStatus + "' WHERE payment_ID = " + paymentId + ";";
		
		try(Connection conn = SQLConnector.getConnection();
				Statement stm = conn.createStatement()) {
			
			int rowsAffected = stm.executeUpdate(sql);
			
			return rowsAffected > 0;
		}catch(SQLException e){
			e.printStackTrace();
			return false;
		}
	
	}
}
