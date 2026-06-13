package Gym_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReservationDBUtils {
	public static int addReservationAndGetCode(Reservation r) {
		
		int invoiceVal = r.getInvoiceNeeded() ? 1 : 0;
		String sqlQuery= "INSERT INTO reservation (date_And_Time, invoice_Needed, reservation_Status, session_Session_Code, customer_Customer_ID) VALUES ('"
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
	
	public static ArrayList<Reservation> getActiveReservations() {
		String sqlQuery= "SELECT * FROM reservation WHERE reservation_Status = 'PENDING' OR reservation_Status = 'COMPLETE' ORDER BY reservation_Status ASC;";
		
		ArrayList<Reservation> activeList = new ArrayList<>();
		
		try (Connection conn= SQLConnector.getConnection();
			Statement stm= conn.createStatement();
			ResultSet res= stm.executeQuery(sqlQuery);){
			
			while (res.next()) {
				
				String dbStatus = res.getString("reservation_Status").toUpperCase();
                ReservationStatus status;
                
                if (dbStatus.equals("CONFIRMED") || dbStatus.equals("COMPLETE")) {
                    status = ReservationStatus.COMPLETE;
                } else if (dbStatus.equals("CANCELLED")) {
                    status = ReservationStatus.CANCELLED;
                } else {
                    status = ReservationStatus.PENDING; 
                }				
				Reservation currentReservation = new Reservation(
						res.getInt("reservation_Code"),
						res.getObject("date_And_Time", LocalDateTime.class),
						res.getBoolean("invoice_Needed"),
						status,
						res.getInt("session_Session_Code"),
						res.getInt("customer_Customer_ID")
					); 
				
				activeList.add(currentReservation);
			}
			
			return activeList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void updateReservationStatus(int id, ReservationStatus status) {
		String sql= "UPDATE reservation SET reservation_Status = '"+status+"' WHERE reservation_Code = " +id+";";
		try(Connection conn = SQLConnector.getConnection(); //establish connection via the class we created
				Statement stm= conn.createStatement();) {
			int rowsAffected= stm.executeUpdate(sql);
			 
			if(rowsAffected>0) {
				System.out.println("");
			}else {
				System.out.println("Something went wrong and the reservation could not be added to db.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean cancelReservationInDB(int resCode) {
		String updateResSql = "UPDATE reservation SET reservation_Status = 'CANCELLED' WHERE reservation_Code = " + resCode;
		
		String updateSessionSql = "UPDATE session "
								+ "SET availability = 1, amount_of_participants = amount_of_participants - 1 "
								+ "WHERE session_Code = (SELECT session_Session_Code FROM reservation WHERE reservation_Code = " + resCode + ")";
		
		try (Connection conn = SQLConnector.getConnection();
				Statement stm = conn.createStatement()){
			
			int rowsAffected = stm.executeUpdate(updateResSql);
			if (rowsAffected > 0) {
				stm.executeUpdate(updateSessionSql);
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static ArrayList<Reservation> displayReservationsByCustomerPhone(String phoneNumber) {
		String sqlQuery = "SELECT r.reservation_Code, r.date_And_Time, r.invoice_Needed, r.reservation_Status, r.session_Session_Code, r.customer_Customer_ID "
                + "FROM reservation r "
                + "JOIN customer c ON r.customer_Customer_ID = c.customer_ID "
                + "WHERE c.phone = '" + phoneNumber + "' "
                + "ORDER BY r.date_And_Time DESC;";
		
		ArrayList<Reservation> reservationList = new ArrayList<>();
		
		try (Connection conn = SQLConnector.getConnection();
				Statement stm = conn.createStatement();
				ResultSet res = stm.executeQuery(sqlQuery)) {
			
			
			while (res.next()) {
				String dbStatus = res.getString("reservation_Status").toUpperCase();
				ReservationStatus status;
				
				if(dbStatus.equals("COMPLETE")) {
					status = ReservationStatus.COMPLETE;
				}else if(dbStatus.equals("CANCELLED")) {
					status = ReservationStatus.CANCELLED;
				}else {
					status = ReservationStatus.PENDING;
				}
				
				Reservation currentReservation = new Reservation(
						res.getInt("reservation_Code"),
						res.getObject("date_And_Time", LocalDateTime.class),
						res.getBoolean("invoice_Needed"),
						status,
						res.getInt("session_Session_Code"),
						res.getInt("customer_Customer_ID")
						);
				reservationList.add(currentReservation);
				
			}
			
			return reservationList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}

