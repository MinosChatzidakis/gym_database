package Gym_project;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReservationDBUtils {
	
	public static void updateReservation(Reservation r) {
	    
	    String sqlQuery = "UPDATE reservation SET "
	            + "date_And_Time = '" + r.getDateAndTime() + "', "
	            + "invoice_Needed = " + (r.getInvoiceNeeded()) + ", "
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
	                res.getObject("date_And_Time", LocalDateTime.class),
	                res.getInt("invoice_Needed"),
	                ReservationStatus.valueOf(res.getString("reservation_Status").toUpperCase()),
	                res.getInt("session_Session_Code"),
	                res.getInt("customer_Customer_ID")
	            );
	        }				    
	    } catch (SQLException e) {
	        System.out.println("Error fetching reservation by ID:");
	        e.printStackTrace();
	    }
	    return null; 
	}

	public static int addReservationAndGetCode(Reservation r) throws SQLException {
	    
	    int invoiceVal = r.getInvoiceNeeded();
	    String sqlQuery = "INSERT INTO reservation (date_And_Time, invoice_Needed, reservation_Status, session_Session_Code, customer_Customer_ID) VALUES ('"
	            + java.sql.Timestamp.valueOf(r.getDateAndTime()) + "', " // Converted to SQL date format safely
	            + invoiceVal + ", '"
	            + r.getReservationStatus().name() + "', "
	            + r.getSessionCode() + ", "
	            + r.getCustomerID() + ")";
	    try (Connection conn = SQLConnector.getConnection();
	         Statement stmt = conn.createStatement()) {
	        
	        // Execute and request generated keys
	        stmt.executeUpdate(sqlQuery, Statement.RETURN_GENERATED_KEYS);
	        
	       
	        try (ResultSet rs = stmt.getGeneratedKeys()) {
	            if (rs.next()) {
	                return rs.getInt(1);  // retrieve the key
	            } else {
	                // Throw an error if the DB fails to generate a key
	                throw new SQLException("Reservation inserted, but no reservation code was generated.");
	            }
	        }
	    }
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
                Timestamp timestamp = res.getTimestamp("date_And_Time"); //res object contains a timestamp
                LocalDateTime datetime = (timestamp != null) ? timestamp.toLocalDateTime() : null; //convert it to a LocalDateTime object
                Reservation currentReservation = new Reservation(
						res.getInt("reservation_Code"),
						datetime,
						res.getInt("invoice_Needed"),
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
				System.out.println("Reservation was added to db successfully");
			}else {
				System.out.println("Something went wrong and the reservation could not be added to db.");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void cancelMultipleReservations(String reservationIds) throws SQLException {
	    //if no IDs were passed => return
		if (reservationIds == null || reservationIds.length() == 0) {
	        System.out.println("No IDs provided.");
	        return;
	    }
	    String sqlQuery = "UPDATE reservation SET reservation_Status = 'CANCELLED' WHERE reservation_Code IN (" + reservationIds + ")";

	    try (Connection conn = SQLConnector.getConnection();
	         Statement stmt = conn.createStatement()) {
	        
	        int rowsAffected = stmt.executeUpdate(sqlQuery);
	        if(rowsAffected>0) {
	        	System.out.println("Updated " + rowsAffected + " reservations successfully.");	        	
	        }else System.out.println("No rows matches the criteria");
	    }
	}
	
	public static ArrayList<Reservation> getUnpaidReservations(){
		String sql= "SELECT r.* FROM reservation r JOIN payment p ON p.reservation_Reservation_Code=r.reservation_Code JOIN session s ON s.session_Code=r.session_Session_Code WHERE p.payment_Status= 'PENDING' AND s.date_And_Time BETWEEN NOW() AND NOW() + INTERVAL 24 HOUR;";
		ArrayList<Reservation> unpaidReservations= new ArrayList<>();
		try(Connection conn= SQLConnector.getConnection();
			Statement stm= conn.createStatement();){
			
			ResultSet res= stm.executeQuery(sql);
			while(res.next()) {
				Reservation currentReservation= new Reservation(
						res.getInt("reservation_Code"),
						res.getObject("date_And_Time", LocalDateTime.class),
						res.getInt("invoice_Needed"),
						ReservationStatus.valueOf(res.getString("reservation_Status").toUpperCase()),
						res.getInt("session_Session_Code"),
						res.getInt("customer_Customer_ID")
						);
				unpaidReservations.add(currentReservation);
			}
			return unpaidReservations;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	//grab all cancelled reservations
	public static ArrayList<Reservation> getCancelledReservations(){
		String sql= "SELECT * FROM reservation WHERE reservation_Status = 'CANCELLED';";
		ArrayList<Reservation> cancelledReservations= new ArrayList<>();
		
		try (Connection conn= SQLConnector.getConnection();
				Statement stm= conn.createStatement()){	
			ResultSet res= stm.executeQuery(sql);
			while(res.next()) {
				Reservation currRes= new Reservation(
						res.getInt("reservation_Code"),
						res.getObject("date_And_Time", LocalDateTime.class),
						res.getInt("invoice_Needed"),
						ReservationStatus.valueOf(res.getString("reservation_Status").toUpperCase()),
						res.getInt("session_Session_Code"),
						res.getInt("customer_Customer_ID")
						);
				cancelledReservations.add(currRes);
			}
			return cancelledReservations;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	//delete reservations based on id
	public static void deleteReservationsById(String ids) {
		//check for valid values given
		if (ids == null || ids.trim().isEmpty()) {
			System.out.println("Cannot delete: Invalid set of reservations given.");
			return;			
		}

	    String sqlQuery = "DELETE FROM reservation WHERE reservation_Code IN (" + ids + ")"; //delete only the reservations that contain the IDs we are after
		try (Connection conn= SQLConnector.getConnection();
				Statement stm= conn.createStatement()){	
			int rowsAffected= stm.executeUpdate(sqlQuery); //execute deletion in database
			System.out.println("Successfully deleted "+rowsAffected+"/" +ids.split(", ").length+" past cancelled reservations");
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
						res.getInt("invoice_Needed"),
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
