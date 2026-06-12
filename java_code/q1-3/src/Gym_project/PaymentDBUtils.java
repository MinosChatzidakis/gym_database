package Gym_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class PaymentDBUtils {
	// get payments that are still pending
	public static ArrayList<PendingPayment> getPendingPayments() {
		// payment_status= pending && session_status= pending
		String sql= "SELECT p.payment_ID as pid, p.payment_Status as pstatus, p.payment_Method as pmethod, p.amount as pamount,"
				+ "r.date_And_Time as rdate,"
				+ "s.date_And_Time as sdate,"
				+ "c.name as cname"
				+ "FROM payment p JOIN reservation r ON p.reservation_Reservation_Code = r.reservation_code"
				+ "JOIN customer c ON r.customer_Customer_ID = c.customer_ID"
				+ "JOIN session s ON s.session_Code = r.session_Session_Code"
				+ "WHERE p.payment_status = 'PENDING' AND r.reservation_status = 'PENDING';";
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
							res.getInt("payment_ID"),
							PaymentMethods.valueOf(res.getString("pmethod")),
							PaymentStatus.valueOf(res.getString("pstatus")),
							res.getString("cname"),
							res.getObject(" rdate ", LocalDateTime.class), // date of reservation
							res.getObject(" sdate ", LocalDateTime.class), //date of session
							res.getFloat("amount")
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
	
}
