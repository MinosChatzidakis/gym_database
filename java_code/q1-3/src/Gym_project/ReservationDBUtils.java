package Gym_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservationDBUtils {
	public static int addReservationAndGetCode(Reservation r) {
		
		int invoiceVal = r.getInvoiceNeeded() ? 1 : 0;
		String sqlQuery= "INSERT INTO reservation (date_And_Time, invoice_Needed, reservation_Status, session_Session_Code, customer_Customer_ID) VALUES ("
				+ r.getDateAndTime() + "', "
				+ invoiceVal + ", '"
				+ r.getReservationStatus().name() + "', "
				+ r.getSessionCode() + ", "
				+ r.getcustomerID() + ")";
		
		try{
			Connection conn = SQLConnector.getConnection(); //establish connection via the class we created
			Statement stm= conn.createStatement();
			int rowsAffected= stm.executeUpdate(sqlQuery, Statement.RETURN_GENERATED_KEYS); //run the query on the database and store amount of rows affected by it
			if(rowsAffected>0) {
				//System.out.println("Reservation was successfully added to db.");
				try (ResultSet generatedKeys = stm.getGeneratedKeys()){
					if(generatedKeys.next()) {
						return generatedKeys.getInt(1);
					}	
				}
			}	
		}catch(SQLException e) {
			System.out.println("Error in adding reservation to db:");
			e.printStackTrace();
		}
		return -1;
	}
	
	public ResultSet getActiveReservations() {
		String sqlQuery= "SELECT * FROM reservation WHERE reservation_status = 'PENDING' OR reservation_status = 'COMPLETE ORDER BY reservation_status;";
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
