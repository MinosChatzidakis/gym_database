//Minos
package Gym_project;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SessionDBUtils { 
	
	public static void addSession(Session s) {
	   
		String sqlQuery = "INSERT INTO session (session_Type, description, max_Participants, duration, price, availability, trainer_Trainer_ID, gym_Gym_Code, date_And_Time, amount_Of_Participants) VALUES ('"
	            + s.getSessionType() + "', '"
	            + s.getDescription() + "', "
	            + s.getMaxParticipants() + ", "
	            + s.getDuration() + ", "
	            + s.getPrice() + ", "
	            + s.getAvailability() + ", "
	            + s.getTrainerTrainerID() + ", "
	            + s.getGymCode() + ", '"
	            + s.getDateAndTime() + "', "
	            + s.getAmountOfParticipants() + ")";
	            
	    try (Connection conn = SQLConnector.getConnection(); 
	         Statement stm = conn.createStatement()) {
	        
	        int rowsAffected = stm.executeUpdate(sqlQuery);
	        if (rowsAffected > 0) {
	            System.out.println("Session was successfully added to the database.");
	        } else {
	            System.out.println("Something went wrong and the session could not be added.");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error in adding session to db:");
	        e.printStackTrace();
	    }
	}
	
	public static void updateSession(Session s) {
	   
	    String sqlQuery = "UPDATE session SET "
	            + "session_Type = '" + s.getSessionType() + "', "
	            + "description = '" + s.getDescription() + "', "
	            + "max_Participants = " + s.getMaxParticipants() + ", "
	            + "duration = " + s.getDuration() + ", "
	            + "price = " + s.getPrice() + ", "
	            + "availability = " + s.getAvailability() + ", "
	            + "trainer_Trainer_ID = " + s.getTrainerTrainerID() + ", "
	            + "gym_Gym_Code = " + s.getGymCode() + ", "
	            + "date_And_Time = '" + s.getDateAndTime() + "', "
	            + "amount_Of_Participants = " + s.getAmountOfParticipants()
	            + " WHERE session_Code = " + s.getSessionCode();
	            
	    try (Connection conn = SQLConnector.getConnection(); 
	         Statement stm = conn.createStatement()) {
	        
	        int rowsAffected = stm.executeUpdate(sqlQuery);
	        if (rowsAffected > 0) {
	            System.out.println("Session data updated successfully in the database.");
	        } else {
	            System.out.println("No changes were made (data might be identical).");
	        }
	    } catch (SQLException e) {
	        System.out.println("Error updating Session data in db:");
	        e.printStackTrace();
	    }
	}
	
	// ΠΡΟΣΘΗΚΗ: Απαραίτητη συνάρτηση για να βρίσκει η Main το session
	public static Session getSessionByID(int id) {
	    String sql = "SELECT * FROM session WHERE session_Code = " + id + " LIMIT 1";
	    try (Connection conn = SQLConnector.getConnection(); 
	         Statement stm = conn.createStatement();
	         ResultSet res = stm.executeQuery(sql)) {		

	        if (res.next()) { 
	            return new Session(
	                res.getInt("session_Code"),
	                res.getString("session_Type"),
	                res.getString("description"),
	                res.getInt("max_Participants"),
	                res.getInt("duration"),
	                res.getFloat("price"),
	                res.getInt("availability"),
	                res.getInt("trainer_Trainer_ID"),
	                res.getInt("gym_Gym_Code"),
	                res.getObject("date_And_Time", LocalDateTime.class),
	                res.getInt("amount_Of_Participants")
	            );
	        }				    
	    } catch (SQLException e) {
	        System.out.println("Error fetching session by ID:");
	        e.printStackTrace();
	    }
	    return null; // Επιστρέφει null αν δεν βρεθεί το session
	}
	
	public static ArrayList<Session> searchSessions(SessionSearch s) {
	    String gymServices = ServicesDBUtils.getUnifiedServicesByGymCode(s.getPreferredGymCode());
	    String requestedServices = s.getAdditionalServices();

	    if (requestedServices != null && !requestedServices.isEmpty()) {
	        if (gymServices == null || gymServices.isEmpty()) {
	        	System.out.println("Fail1");
	        	return new ArrayList<>();
	        }
	        List<String> offeredList = java.util.Arrays.asList(gymServices.split(","));
	        for (int i = 0; i < offeredList.size(); i++) {
	        	offeredList.set(i, offeredList.get(i).trim());
	        }
	        List<String> neededList = java.util.Arrays.asList(requestedServices.split(","));
	        for (int i = 0; i < neededList.size(); i++) {
	        	neededList.set(i, neededList.get(i).trim());
	        }

	        if (!offeredList.containsAll(neededList)) {
	            System.out.println("Fail2");
	        	return new ArrayList<>();
	        }
	    }
	    
	    System.out.println("Starting sb building");
	    StringBuilder sbQuery = new StringBuilder();
	    sbQuery.append("SELECT * FROM session WHERE availability = 1");
	    if (s.getPreferredGymCode() != -1) {
	        sbQuery.append(" AND gym_Gym_Code = ").append(s.getPreferredGymCode());
	    }
	    if (!s.getTrainingType().isEmpty()) {
	        sbQuery.append(" AND session_Type = '").append(s.getTrainingType()).append("'");
	    }
	    if (!s.getDate().isEmpty()) {
	        sbQuery.append(" AND date_And_Time LIKE '%").append(s.getDate()).append("%'");
	    }
	    if (s.getTrainerId() != -1) {
	        sbQuery.append(" AND trainer_Trainer_ID = ").append(s.getTrainerId());
	    }
	    sbQuery.append(";");
	    
	    ArrayList<Session> availableSessions = new ArrayList<>();
	    
	    try (Connection conn = SQLConnector.getConnection(); 
	         Statement stm = conn.createStatement();
	 		 ResultSet res = stm.executeQuery(sbQuery.toString())) {
	        
	    	System.out.println("final query: " + sbQuery.toString());
	        while (res.next()) {
	            // ΔΙΟΡΘΩΘΗΚΕ: Προστέθηκε η 11η παράμετρος (amount_of_participants) στο τέλος του Constructor
	            Session currentSession = new Session(
	                res.getInt("session_code"),
	                res.getString("session_type"),
	                res.getString("description"),
	                res.getInt("max_participants"),
	                res.getInt("duration"),
	                res.getFloat("price"),
	                res.getInt("availability"),
	                res.getInt("trainer_trainer_id"),
	                res.getInt("gym_Gym_Code"),
	                res.getObject("date_And_Time", LocalDateTime.class),
	                res.getInt("amount_Of_Participants") // <-- Η προσθήκη έγινε εδώ
	            );
	            availableSessions.add(currentSession);
	        }
	        return availableSessions;
	        
	    } catch(SQLException e) {
	        e.printStackTrace();
	        return null; 
	    }
	}
	
	public static void testQuery() {
		String sqlQuery = "SELECT * FROM session WHERE availability = 1 AND gym_Gym_Code = 7516 AND session_Type = 'PILATES' AND date_And_Time LIKE '%16/06/2026%' AND trainer_Trainer_ID = 2;";
		try (Connection conn = SQLConnector.getConnection();
			 Statement stm = conn.createStatement();
			 ResultSet res = stm.executeQuery(sqlQuery)) {
			while(res.next()) {
				System.out.println(res.getInt("session_Code"));
			}
			System.out.println("Done");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void checkAndUpdateAvailability(Session session) {
		String countQuery = "SELECT COUNT(*) FROM reservation WHERE session_Session_Code = " + session.getSessionCode() 
        + " AND reservation_Status != 'CANCELLED';";
		
		try (Connection conn = SQLConnector.getConnection();
		         Statement stm = conn.createStatement()) {
		        
		        try (ResultSet res = stm.executeQuery(countQuery)) {
		            if (res.next()) {
		                int realParticipantsCount = res.getInt(1);
		                
		                session.setAmountOfParticipants(realParticipantsCount);
		                if (session.getAmountOfParticipants() >= session.getMaxParticipants()) {
		                    session.setAvailability(0);
		                    String updateQuery = "UPDATE session SET availability = 0 WHERE session_code = " + session.getSessionCode() + ";";
		                    stm.executeUpdate(updateQuery);
		                }
		            }
		        }
	
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}


public static void freeUpSpaceInMultipleSessions(String sessionIds) throws SQLException{
	//if no IDs were passed => return
	if (sessionIds == null || sessionIds.length() == 0) {
        System.out.println("No IDs provided.");
        return;
    }
	//remove one participant from each session and then check if it's still available
	String sqlQuery = "UPDATE sessionn SET amount_Of_Participants= amount_Of_Participants -1, availability = CASE WHEN amount_Of<Participants < max_Participants THEN 1 WHEN amount_Of<Participants = max_Participants THEN 0 END WHERE session_Code IN (" + sessionIds + ");";
    try (Connection conn = SQLConnector.getConnection();
         Statement stmt = conn.createStatement()) {
        	
        int rowsAffected = stmt.executeUpdate(sqlQuery);
        if(rowsAffected>0) {
        	System.out.println("Updated " + rowsAffected + " sessions successfully.");	        	
        }else System.out.println("No rows matches the criteria");
	    }
	}
}
